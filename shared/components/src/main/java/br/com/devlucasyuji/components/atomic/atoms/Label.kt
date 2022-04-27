package br.com.devlucasyuji.components.atomic.atoms

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.devlucasyuji.components.animation.Animate.Animated
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.props.Text

@Composable
fun Label(
    text: Text,
    modifier: Modifier = Modifier,
    animation: Animation = Animation.None,
) {
    animation.Animated {
        Text(
            text = text.text,
            modifier = modifier,
            style = MaterialTheme.typography.bodyMedium.copy(
                MaterialTheme.colorScheme.onPrimary
            ).merge(text.style)
        )
    }
}