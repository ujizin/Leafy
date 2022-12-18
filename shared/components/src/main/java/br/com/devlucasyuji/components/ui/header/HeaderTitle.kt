package br.com.devlucasyuji.components.ui.header

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import br.com.devlucasyuji.components.extensions.Empty
import br.com.devlucasyuji.components.ui.animated.AnimatedText
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.themes.CameraReminderTheme

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
    CameraReminderTheme {
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
    CameraReminderTheme {
        HeaderTitle(
            title = "Hi Lucas",
            animation = Animation.None
        )
    }
}
