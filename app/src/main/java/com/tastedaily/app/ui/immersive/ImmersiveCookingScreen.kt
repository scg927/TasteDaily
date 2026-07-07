package com.tastedaily.app.ui.immersive

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.NavigateBefore
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.tastedaily.app.data.AppContainer
import com.tastedaily.app.data.repository.RecipeRepository
import com.tastedaily.app.ui.components.Format
import com.tastedaily.app.ui.video.VideoPlayer
import com.tastedaily.core.model.CookingStep
import com.tastedaily.core.model.Dish

@Composable
fun ImmersiveCookingScreen(
    dishId: String,
    onExit: () -> Unit,
    viewModel: ImmersiveViewModel = viewModel(),
) {
    LaunchedEffect(dishId) { viewModel.load(dishId) }
    val dish = viewModel.dish

    val context = LocalContext.current
    val view = LocalView.current

    // Immersive mode: draw edge-to-edge, hide system bars, keep the screen awake while cooking.
    DisposableEffect(Unit) {
        val window = (context as? android.app.Activity)?.window
        val controller = window?.let { WindowInsetsControllerCompat(it, view) }
        window?.let { WindowCompat.setDecorFitsSystemWindows(it, false) }
        controller?.hide(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE)
        controller?.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        view.keepScreenOn = true
        onDispose {
            view.keepScreenOn = false
            window?.let { WindowCompat.setDecorFitsSystemWindows(it, true) }
            controller?.show(WindowInsetsControllerCompat.BEHAVIOR_DEFAULT)
        }
    }

    BackHandler { onExit() }

    if (dish == null || dish.steps.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {
            Text("没有可用的烹饪步骤", color = Color.White)
        }
        return
    }

    var stepIndex by remember { mutableStateOf(0) }
    val total = dish.steps.size
    val step = dish.steps[stepIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        // Top control bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                onClick = onExit,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.2f)),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 6.dp),
            ) {
                Icon(Icons.Filled.Close, contentDescription = "退出", tint = Color.White)
                Spacer(Modifier.width(6.dp))
                Text("退出", color = Color.White)
            }
            Spacer(Modifier.width(12.dp))
            Text(
                dish.name,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
            )
        }

        LinearProgressIndicator(
            progress = { (stepIndex + 1f) / total },
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp),
        )

        // Step media
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f),
            contentAlignment = Alignment.Center,
        ) {
            StepMedia(step = step)
        }

        // Step text
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 20.dp, vertical = 16.dp),
        ) {
            Text(
                "第 ${step.index} 步 / 共 $total 步",
                color = Color.White.copy(alpha = 0.7f),
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(Modifier.height(8.dp))
            Text(step.title, color = Color.White, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))
            Text(step.instruction, color = Color.White.copy(alpha = 0.9f), style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(12.dp))
            Text(
                "建议用时 ${Format.durationLabel(step.durationSeconds / 60)}",
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        // Bottom controls
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                onClick = { if (stepIndex > 0) stepIndex-- },
                enabled = stepIndex > 0,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.2f)),
            ) {
                Icon(Icons.AutoMirrored.Filled.NavigateBefore, contentDescription = "上一步", tint = Color.White)
                Spacer(Modifier.width(4.dp))
                Text("上一步", color = Color.White)
            }
            Text(
                "${stepIndex + 1} / $total",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
            )
            if (stepIndex < total - 1) {
                Button(onClick = { stepIndex++ }) {
                    Text("下一步")
                    Spacer(Modifier.width(4.dp))
                    Icon(Icons.AutoMirrored.Filled.NavigateNext, contentDescription = "下一步", tint = Color.White)
                }
            } else {
                Button(onClick = onExit, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)) {
                    Icon(Icons.Filled.Check, contentDescription = null, tint = Color.White)
                    Spacer(Modifier.width(4.dp))
                    Text("完成烹饪", color = Color.White)
                }
            }
        }
    }
}

@Composable
private fun StepMedia(step: CookingStep) {
    val videoUrl = step.videoUrl
    val imageUrl = step.imageUrl
    if (videoUrl != null) {
        // VideoPlayer 内部已做异常捕获与错误占位，这里直接调用即可
        VideoPlayer(
            videoUrl = videoUrl,
            modifier = Modifier.fillMaxSize(),
        )
    } else if (imageUrl != null) {
        AsyncImage(
            model = imageUrl,
            contentDescription = step.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
    } else {
        Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray), contentAlignment = Alignment.Center) {
            Text("🍳", color = Color.White)
        }
    }
}
