package com.tastedaily.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.tastedaily.app.ui.nav.TasteDailyNavGraph
import com.tastedaily.app.ui.theme.TasteDailyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TasteDailyTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TasteDailyNavGraph()
                }
            }
        }
    }
}
