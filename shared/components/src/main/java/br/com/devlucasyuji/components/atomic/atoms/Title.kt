package br.com.devlucasyuji.components.atomic.atoms

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.devlucasyuji.components.animation.Animate.Animated
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.props.Text
import br.com.devlucasyuji.themes.CameraReminderTheme

@Composable
internal fun Title(
    text: Text,
    modifier: Modifier = Modifier,
    animation: Animation = Animation.None,
) {
    animation.Animated {
        Text(
            text = text.text,
            modifier = modifier,
            style = MaterialTheme.typography.titleLarge
                .copy(MaterialTheme.colorScheme.onPrimary)
                .merge(text.style),
        )
    }
}

@Preview
@Composable
private fun PreviewTitle() {
    CameraReminderTheme {
        Title(Text("Hello Lucas"))
    }
}
