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

private val darkColorScheme = darkColorScheme(
    primary = Color.Green,
    secondary = Color.Cyan,
    tertiary = Color.Cloud,
    surface = Color.Green,
    background = Color.Cloud,
    onPrimary = Color.Cloud,
    onSecondary = Color.Gray,
    onBackground = Color.Gray,
    secondaryContainer = Color.Cloud,
    onSecondaryContainer = Color.DarkGreen,
    outline = Color.Green,
    surfaceVariant = Color.Cyan,
    onSurfaceVariant = Color.Cloud,
    onSurface = Color.DarkGray
)

private val lightColorScheme = lightColorScheme(
    primary = Color.Green,
    secondary = Color.Cyan,
    tertiary = Color.Cloud,
    surface = Color.Green,
    background = Color.Cloud,
    onPrimary = Color.Cloud,
    onSecondary = Color.Gray,
    outline = Color.Green,
    onBackground = Color.Gray,
    secondaryContainer = Color.Cloud,
    onSecondaryContainer = Color.DarkGreen,
    surfaceVariant = Color.Cyan,
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

        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = getTypography(colorScheme),
        content = {
            ProvideTextStyle(value = MaterialTheme.typography.bodyMedium, content = content)
        }
    )
}
