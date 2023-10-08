package com.dev.gm.ui.theme

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@SuppressLint("ConflictingOnColor")
private val LightThemeColors = lightColors(
    primary = Color(0xFF546ee5),
    primaryVariant = Color(0xFF8c9cff),
    onPrimary = White,
    secondary = Color(0xFF46807E),
    secondaryVariant = Color(0xFF75b0ad),
    onSecondary = Black1,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = Color(0xFF070d2d),
    onBackground = White,
    surface = Color(0xFF161A37),
    onSurface = Gray300,
)

private val DarkThemeColors = darkColors(
    primary = Color(0xFF546ee5),
    primaryVariant = Color(0xFF8c9cff),
    onPrimary = White,
    secondary = Color(0xFF46807E),
    secondaryVariant = Color(0xFF75b0ad),
    error = RedErrorLight,
    background = Color.Black,
    onBackground = Color.White,
    surface = Black1,
    onSurface = Color.White,
)

@Composable
fun GmTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    androidx.compose.material.MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = KaiseioptiTypography,
        shapes = AppShapes,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = androidx.compose.material.MaterialTheme.colors.surface)
        ) {
            content()
        }
    }
}