package br.com.devlucasyuji.components.ui.button

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    onClick: OnClick,
    content: @Composable Content = {},
) {
    Material3Button(
        modifier = modifier,
        onClick = onClick,
    ) {
        text?.let { text ->
            Text(
                color = MaterialTheme.colorScheme.onPrimary,
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