package br.com.devlucasyuji.components.ui.button

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.devlucasyuji.components.extensions.Content
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.themes.CameraReminderTheme
import androidx.compose.material3.Button as Material3Button

@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String? = null,
    enabled: Boolean = true,
    onClick: OnClick,
    content: @Composable Content = {},
) {
    Material3Button(
        enabled = enabled,
        modifier = modifier,
        onClick = onClick,
    ) {
        text?.let { text ->
            val contentColor by rememberUpdatedState(
                newValue = when {
                    enabled -> MaterialTheme.colorScheme.onPrimary
                    else -> MaterialTheme.colorScheme.onSurface
                }
            )
            Text(
                color = contentColor,
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