package com.ujizin.leafy.components.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.components.extensions.Content
import com.ujizin.leafy.components.ui.animated.AnimatedButtonIcon
import com.ujizin.leafy.components.ui.animated.animation.Animate.Animated
import com.ujizin.leafy.components.ui.animated.animation.Animation
import com.ujizin.leafy.components.ui.header.HeaderTitle
import com.ujizin.leafy.components.ui.image.Icons
import com.ujizin.leafy.themes.LeafyTheme

@Composable
fun Section(
    modifier: Modifier = Modifier,
    headerPaddingValues: PaddingValues = PaddingValues(top = 32.dp, start = 20.dp, end = 20.dp),
    title: String,
    subTitle: String = "",
    trailingIcon: @Composable Content? = null,
    leadingIcon: @Composable Content? = null,
    headerAnimation: Animation = Animation.SlideToTop,
    animation: Animation = Animation.None,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    Animated(animation = animation) {
        Column(modifier = modifier) {
            HeaderSection(
                modifier = Modifier.padding(headerPaddingValues),
                trailingIcon = trailingIcon,
                leadingIcon = leadingIcon,
                title = title,
                subTitle = subTitle,
                headerAnimation = headerAnimation,
            )
            content()
        }
    }
}

@Composable
private fun HeaderSection(
    modifier: Modifier = Modifier,
    trailingIcon: @Composable Content?,
    leadingIcon: @Composable Content?,
    title: String,
    subTitle: String,
    headerAnimation: Animation,
) {
    val paddingTop by animateDpAsState(targetValue = if (trailingIcon != null || leadingIcon != null) 24.dp else 0.dp)
    Column(modifier) {
        Toolbar(trailingIcon = trailingIcon, leadingIcon = leadingIcon)

        HeaderTitle(
            title = title,
            subTitle = subTitle,
            animation = headerAnimation,
            modifier = Modifier.padding(top = paddingTop)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewSection() {
    LeafyTheme {
        Section(
            modifier = Modifier.padding(top = 32.dp),
            title = "Hello Lucas",
            subTitle = "Welcome back!",
            leadingIcon = { AnimatedButtonIcon(icon = Icons.Hamburger) },
            trailingIcon = { AnimatedButtonIcon(icon = Icons.Magnifier) },
            headerAnimation = Animation.None
        )
    }
}
