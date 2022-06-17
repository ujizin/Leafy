package br.com.devlucasyuji.components.atomic.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.atomic.atoms.Label
import br.com.devlucasyuji.components.atomic.atoms.Title
import br.com.devlucasyuji.components.extensions.Empty
import br.com.devlucasyuji.components.props.Text
import br.com.devlucasyuji.themes.CameraReminderTheme

@Composable
fun HeaderTitle(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(horizontal = 20.dp),
    animation: Animation = Animation.SlideToTop,
    title: Text,
    subTitle: Text = Text()
) {
    Column(modifier.padding(paddingValues)) {
        Title(title, animation = animation)
        if (subTitle.text != String.Empty) {
            Label(
                text = subTitle,
                animation = animation.copy(delayMillis = Animation.MediumDelay),
            )
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
