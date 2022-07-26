package br.com.devlucasyuji.themes

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color.DarkCyan,
    secondary = Color.Cyan,
    tertiary = Color.Cloud,
    surface = Color.DarkCyan,
    background = Color.Cloud,
    onPrimary = Color.Gray,
    secondaryContainer = Color.Cloud,
    onSecondaryContainer = Color.DarkCyan,
    surfaceVariant = Color.DarkCyan,
    onSurfaceVariant = Color.Cloud,
    onSurface = Color.Cyan
)

private val LightColorScheme = lightColorScheme(
    primary = Color.DarkCyan,
    secondary = Color.Cyan,
    tertiary = Color.Cloud,
    surface = Color.DarkCyan,
    background = Color.Cloud,
    onPrimary = Color.Gray,
    secondaryContainer = Color.Cloud,
    onSecondaryContainer = Color.DarkCyan,
    surfaceVariant = Color.DarkCyan,
    onSurfaceVariant = Color.Cloud,
    onSurface = Color.Cyan
)

@Composable
fun CameraReminderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    ProvideTextStyle(value = MaterialTheme.typography.bodyMedium) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = getTypography(colorScheme),
            content = content
        )
    }
}
