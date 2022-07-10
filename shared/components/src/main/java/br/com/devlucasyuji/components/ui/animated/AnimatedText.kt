package br.com.devlucasyuji.components.atomic.atoms

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import br.com.devlucasyuji.components.animation.Animate.Animated
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.themes.CameraReminderTheme

@Composable
internal fun AnimatedText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = TextStyle(),
    animation: Animation = Animation.None,
) {
    animation.Animated {
        Text(
            text = text,
            modifier = modifier,
            style = style,
        )
    }
}

@Preview
@Composable
private fun PreviewTitle() {
    CameraReminderTheme {
        AnimatedText(text = "Hello Lucas")
    }
}
