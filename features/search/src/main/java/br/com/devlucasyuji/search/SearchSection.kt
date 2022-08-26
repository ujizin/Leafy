package br.com.devlucasyuji.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.devlucasyuji.components.Section
import br.com.devlucasyuji.components.extensions.section
import br.com.devlucasyuji.components.ui.animated.AnimatedIcon
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.components.ui.image.Icons

@Composable
fun SearchSection() {
    Section(
        modifier = Modifier.section(),
        leadingIcon = {
            AnimatedIcon(
                icon = Icons.Hamburger,
                animation = Animation.SlideToTop,
            )
        },
        trailingIcon = {
            AnimatedIcon(
                icon = Icons.Settings,
                animation = Animation.SlideToTop
            )
        },
        title = "Search"
    )
}