package com.tastedaily.app.ui.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

/**
 * 视频播放状态：用于驱动 UI 层的加载/错误占位。
 */
sealed class VideoPlaybackState {
    data object Loading : VideoPlaybackState()
    data object Playing : VideoPlaybackState()
    data class Error(val message: String) : VideoPlaybackState()
}

@Composable
fun VideoPlayer(
    videoUrl: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var playbackState by remember { mutableStateOf<VideoPlaybackState>(VideoPlaybackState.Loading) }

    // 安全创建 ExoPlayer：用 try-catch 防止 Builder/build 阶段抛异常导致崩溃
    val exoPlayer = remember {
        try {
            ExoPlayer.Builder(context).build().apply {
                addListener(object : Player.Listener {
                    override fun onPlaybackStateChanged(state: Int) {
                        when (state) {
                            Player.STATE_READY -> {
                                playbackState = VideoPlaybackState.Playing
                            }
                            Player.STATE_BUFFERING -> {
                                playbackState = VideoPlaybackState.Loading
                            }
                            Player.STATE_ENDED -> {
                                // 播放结束保持 Playing 态，用户可点击重播
                            }
                            Player.STATE_IDLE -> {
                                // 空闲态，不做处理
                            }
                        }
                    }

                    override fun onPlayerErrorChanged(error: PlaybackException?) {
                        if (error != null) {
                            playbackState = VideoPlaybackState.Error(
                                message = error.message ?: "视频播放失败"
                            )
                        }
                    }

                    override fun onPlayerError(error: PlaybackException) {
                        playbackState = VideoPlaybackState.Error(
                            message = error.message ?: "视频播放失败"
                        )
                    }
                })
                setMediaItem(MediaItem.fromUri(videoUrl))
                prepare()
                playWhenReady = true
            }
        } catch (e: Exception) {
            playbackState = VideoPlaybackState.Error(
                message = e.message ?: "播放器初始化失败"
            )
            null
        }
    }

    // 绑定生命周期： onStop 时暂停播放，onStart 时恢复，防止后台播放冲突
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_STOP -> exoPlayer?.pause()
                Lifecycle.Event.ON_START -> {
                    if (exoPlayer?.playbackState == Player.STATE_READY) {
                        exoPlayer.play()
                    }
                }
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            exoPlayer?.release()
        }
    }

    Box(modifier = modifier.background(Color.Black)) {
        if (exoPlayer != null) {
            AndroidView(
                factory = { ctx ->
                    PlayerView(ctx).apply {
                        player = exoPlayer
                        useController = true
                        setShowBuffering(PlayerView.SHOW_BUFFERING_WHEN_PLAYING)
                    }
                },
                modifier = Modifier.fillMaxSize(),
            )
        }

        // 加载中
        if (playbackState is VideoPlaybackState.Loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }

        // 出错占位 UI（不崩溃，给用户友好提示 + 重试）
        if (playbackState is VideoPlaybackState.Error) {
            val errorMsg = (playbackState as VideoPlaybackState.Error).message
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    Icons.Filled.Error,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.6f),
                    modifier = Modifier.height(48.dp),
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    "视频暂时无法播放",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    errorMsg,
                    color = Color.White.copy(alpha = 0.5f),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                )
                Spacer(Modifier.height(16.dp))
                IconButton(onClick = {
                    playbackState = VideoPlaybackState.Loading
                    try {
                        exoPlayer?.setMediaItem(MediaItem.fromUri(videoUrl))
                        exoPlayer?.prepare()
                        exoPlayer?.playWhenReady = true
                    } catch (e: Exception) {
                        playbackState = VideoPlaybackState.Error(e.message ?: "重试失败")
                    }
                }) {
                    Icon(
                        Icons.Filled.PlayArrow,
                        contentDescription = "重试",
                        tint = Color.White,
                        modifier = Modifier.height(40.dp),
                    )
                }
            }
        }
    }
}
