package br.com.devlucasyuji.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.devlucasyuji.components.extensions.screenPadding
import br.com.devlucasyuji.components.ui.Section
import br.com.devlucasyuji.components.ui.animated.AnimatedButtonIcon
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.components.ui.image.Icons

@Composable
fun SearchSection() {
    Box(Modifier.fillMaxSize()) {
        Section(
            modifier = Modifier.screenPadding(),
            leadingIcon = {
                AnimatedButtonIcon(
                    icon = Icons.Hamburger,
                    animation = Animation.SlideToTop,
                )
            },
            trailingIcon = {
                AnimatedButtonIcon(
                    icon = Icons.Settings,
                    animation = Animation.SlideToTop
                )
            },
            title = "Search"
        )
    }
}