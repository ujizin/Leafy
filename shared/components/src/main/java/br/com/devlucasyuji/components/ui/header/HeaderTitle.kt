package br.com.devlucasyuji.components.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.animation.Animate.Animated
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.extensions.Empty
import br.com.devlucasyuji.themes.CameraReminderTheme

@Composable
fun HeaderTitle(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(horizontal = 20.dp),
    animation: Animation = Animation.SlideToTop,
    title: String,
    subTitle: String = ""
) {
    Column(modifier.padding(paddingValues)) {
        animation.Animated {
            Text(
                text = title,
                modifier = modifier,
                style = MaterialTheme.typography.titleLarge
                    .copy(MaterialTheme.colorScheme.onPrimary),
            )
        }
        if (subTitle != String.Empty) {
            animation.copy(delayMillis = Animation.MediumDelay).Animated {
                Text(text = subTitle)
            }
        }
    }
}

@Preview("Header Title with Subtitle")
@Composable
private fun PreviewHeaderTitleWithSubtitle() {
    CameraReminderTheme {
        HeaderTitle(
            title = Text("Hi Lucas"),
            subTitle = Text("Welcome back!"),
            animation = Animation.None
        )
    }
}

@Preview("Header Title")
@Composable
private fun PreviewHeaderTitle() {
    CameraReminderTheme {
        HeaderTitle(
            title = Text("Hi Lucas"),
            animation = Animation.None
        )
    }
}
