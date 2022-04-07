package br.com.devlucasyuji.components.atomic.atoms

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.devlucasyuji.components.animation.Animate.Animated
import br.com.devlucasyuji.components.animation.Animation

@Composable
fun Label(
    text: String,
    modifier: Modifier = Modifier,
    animation: Animation = Animation.None,
) {
    animation.Animated {
        Text(
            text = text,
            modifier = modifier,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}