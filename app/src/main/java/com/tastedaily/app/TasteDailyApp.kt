package com.tastedaily.app

import android.app.Application
import android.util.Log
import com.tastedaily.app.data.AppContainer

class TasteDailyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppContainer.init(this)

        // 全局异常捕获：作为最后一道防线，防止 ExoPlayer / 媒体编解码器
        // 在特定机型（如荣耀 MagicOS / 华为 EMUI）上抛出未捕获异常导致闪退。
        // 捕获后仅记录日志，不退出进程，让用户可以继续使用其他功能。
        val previousHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("TasteDaily", "Uncaught exception on ${thread.name}", throwable)
            // 仅对媒体播放相关的异常做静默处理，其他异常交给默认 handler
            val isMediaRelated = throwable is Exception &&
                (
                    throwable::class.qualifiedName?.contains("MediaCodec", true) == true ||
                        throwable::class.qualifiedName?.contains("ExoPlayer", true) == true ||
                        throwable.stackTrace.any {
                            it.className.contains("media3", true) ||
                                it.className.contains("exoplayer", true) ||
                                it.className.contains("VideoPlayer", true)
                        }
                )
            if (isMediaRelated) {
                Log.w("TasteDaily", "Media-related crash intercepted, suppressing to avoid app exit", throwable)
                // 静默吞掉媒体异常，不退出 app
            } else {
                // 非媒体异常，交给系统默认处理（正常崩溃）
                previousHandler?.uncaughtException(thread, throwable)
            }
        }
    }
}
