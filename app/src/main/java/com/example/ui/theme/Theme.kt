package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = JavaOrangePrimary,
    onPrimary = Color.White,
    primaryContainer = JavaOrangeDark,
    onPrimaryContainer = Color.White,
    secondary = JavaCyanSecondary,
    onSecondary = Color.White,
    secondaryContainer = JavaCyanDark,
    onSecondaryContainer = Color.White,
    tertiary = JavaPurpleTertiary,
    background = BackgroundDark,
    onBackground = TextPrimaryDark,
    surface = SurfaceDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = TextSecondaryDark,
    outline = BorderDark,
    error = JavaRoseError,
    onError = Color.White
)

private val AmoledColorScheme = darkColorScheme(
    primary = JavaOrangePrimary,
    onPrimary = Color.White,
    primaryContainer = JavaOrangeDark,
    onPrimaryContainer = Color.White,
    secondary = JavaCyanLight,
    onSecondary = Color.Black,
    tertiary = JavaPurpleTertiary,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color(0xFF070B14),
    onSurface = Color.White,
    surfaceVariant = Color(0xFF101626),
    onSurfaceVariant = Color(0xFFCBD5E1),
    outline = Color(0xFF1E293B),
    error = JavaRoseError,
    onError = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = JavaOrangePrimary,
    onPrimary = Color.White,
    primaryContainer = JavaOrangeLight,
    onPrimaryContainer = Color(0xFF7C2D12),
    secondary = JavaCyanSecondary,
    onSecondary = Color.White,
    secondaryContainer = JavaCyanLight,
    onSecondaryContainer = Color(0xFF0369A1),
    tertiary = JavaPurpleTertiary,
    background = BackgroundLight,
    onBackground = TextPrimaryLight,
    surface = SurfaceLight,
    onSurface = TextPrimaryLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = TextSecondaryLight,
    outline = BorderLight,
    error = JavaRoseError,
    onError = Color.White
)

@Composable
fun LearnJavaTheme(
    themePreference: String = "dark", // "dark", "light", "amoled", "system"
    dynamicColor: Boolean = false, // developer tech palette prioritized
    content: @Composable () -> Unit
) {
    val isSystemDark = isSystemInDarkTheme()
    val isDark = when (themePreference) {
        "light" -> false
        "amoled" -> true
        "dark" -> true
        else -> isSystemDark
    }

    val context = LocalContext.current
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (isDark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        themePreference == "amoled" -> AmoledColorScheme
        isDark -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
