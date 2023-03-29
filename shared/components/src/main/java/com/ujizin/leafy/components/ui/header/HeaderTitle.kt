package com.ujizin.leafy.components.ui.header

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.ujizin.leafy.components.extensions.Empty
import com.ujizin.leafy.components.ui.animated.AnimatedText
import com.ujizin.leafy.components.ui.animated.animation.Animation
import com.ujizin.leafy.themes.LeafyTheme

@Composable
fun HeaderTitle(
    modifier: Modifier = Modifier,
    animation: Animation = Animation.SlideToTop,
    title: String,
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge,
    subTitle: String = String.Empty,
    subTitleStyle: TextStyle = MaterialTheme.typography.bodyMedium,
) {
    Column(modifier) {
        AnimatedText(animation = animation, text = title, style = titleStyle)
        if (subTitle != String.Empty) {
            AnimatedText(
                animation = animation.copy(delayMillis = animation.durationMillis),
                text = subTitle,
                style = subTitleStyle
            )
        }
    }
}

@Preview("Header Title with Subtitle")
@Composable
private fun PreviewHeaderTitleWithSubtitle() {
    LeafyTheme {
        HeaderTitle(
            title = "Hi Lucas",
            subTitle = "Welcome back!",
            animation = Animation.None
        )
    }
}

@Preview("Header Title")
@Composable
private fun PreviewHeaderTitle() {
    LeafyTheme {
        HeaderTitle(
            title = "Hi Lucas",
            animation = Animation.None
        )
    }
}
