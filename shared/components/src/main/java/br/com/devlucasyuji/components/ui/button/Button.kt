package br.com.devlucasyuji.components.ui.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.extensions.Content
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.ui.animated.AnimatedText
import br.com.devlucasyuji.themes.CameraReminderTheme
import androidx.compose.material3.Button as Material3Button

@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String? = null,
    enabled: Boolean = true,
    onClick: OnClick,
    capitalize: Boolean = true,
    shape: Shape = RoundedCornerShape(4.dp),
    content: @Composable Content = {},
) {
    Material3Button(
        enabled = enabled,
        modifier = modifier,
        shape = shape,
        onClick = onClick,
    ) {
        text?.let { text ->
            val contentColor by rememberUpdatedState(
                newValue = when {
                    enabled -> MaterialTheme.colorScheme.onPrimary
                    else -> MaterialTheme.colorScheme.onSurface
                }
            )
            AnimatedText(
                color = contentColor,
                capitalize = capitalize,
                text = text,
            )
        } ?: content()
    }
}

@Preview
@Composable
fun PreviewButton() {
    CameraReminderTheme {
        Button(text = "Hello world!", onClick = {})
    }
}