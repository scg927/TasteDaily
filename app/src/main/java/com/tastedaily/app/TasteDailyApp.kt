package com.tastedaily.app

import android.app.Application
import com.tastedaily.app.data.AppContainer

class TasteDailyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppContainer.init(this)
    }
}
