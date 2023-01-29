package br.com.devlucasyuji.components.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.extensions.Content
import br.com.devlucasyuji.components.ui.animated.AnimatedIcon
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.components.ui.header.HeaderTitle
import br.com.devlucasyuji.components.ui.image.Icons
import br.com.devlucasyuji.themes.CameraReminderTheme

@Composable
fun Section(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String = "",
    trailingIcon: @Composable Content? = null,
    leadingIcon: @Composable Content? = null,
    headerAnimation: Animation = Animation.SlideToTop,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    val titlePadding = PaddingValues(
        top = when {
            trailingIcon != null || leadingIcon != null -> 24.dp
            else -> 0.dp
        }
    )
    Column(modifier) {
        Toolbar(trailingIcon = trailingIcon, leadingIcon = leadingIcon)

        HeaderTitle(
            title = title,
            subTitle = subTitle,
            animation = headerAnimation,
            modifier = Modifier.padding(titlePadding)
        )

        content()
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewSection() {
    CameraReminderTheme {
        Section(
            modifier = Modifier.padding(top = 32.dp),
            title = "Hello Lucas",
            subTitle = "Welcome back!",
            leadingIcon = { AnimatedIcon(icon = Icons.Hamburger) },
            trailingIcon = { AnimatedIcon(icon = Icons.Magnifier) },
            headerAnimation = Animation.None
        )
    }
}
