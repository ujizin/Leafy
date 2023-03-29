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
    primary = Color.Green800,
    secondary = Color.Green200,
    tertiary = Color.Gray100,
    surface = Color.Gray200,
    background = Color.Gray100,
    onPrimary = Color.Gray100,
    onSecondary = Color.Gray800,
    onBackground = Color.Gray800,
    secondaryContainer = Color.Green200,
    onSecondaryContainer = Color.Gray800,
    outline = Color.Green800,
    surfaceVariant = Color.Green200,
    onSurface = Color.Gray900
)

private val lightColorScheme = lightColorScheme(
    primary = Color.Green800,
    secondary = Color.Green200,
    tertiary = Color.Gray100,
    surface = Color.Gray200,
    background = Color.Gray100,
    onPrimary = Color.Gray100,
    onSecondary = Color.Gray800,
    outline = Color.Green800,
    onBackground = Color.Gray800,
    secondaryContainer = Color.Green200,
    onSecondaryContainer = Color.Gray800,
    surfaceVariant = Color.Green200,
    onSurface = Color.Gray900
)

@Composable
fun LeafyTheme(
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
