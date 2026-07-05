package com.tastedaily.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = WarmPrimary,
    onPrimary = WarmOnPrimary,
    primaryContainer = WarmSecondary,
    secondary = WarmSecondary,
    tertiary = WarmTertiary,
    background = WarmBackground,
    surface = WarmSurface,
    onBackground = WarmOnBackground,
    onSurface = WarmOnSurface,
    outline = WarmOutline,
)

private val DarkColors = darkColorScheme(
    primary = WarmSecondary,
    secondary = WarmSecondary,
    tertiary = WarmTertiary,
)

@Composable
fun TasteDailyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = TasteDailyTypography,
        content = content,
    )
}
