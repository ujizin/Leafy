package br.com.devlucasyuji.components.ui.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.themes.CameraReminderTheme

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    value: String,
    singleLine: Boolean = true,
    shape: Shape = RoundedCornerShape(4.dp),
    paddingValues: PaddingValues = PaddingValues(16.dp),
    onValueChange: (String) -> Unit,
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        textStyle = LocalTextStyle.current,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        decorationBox = { innerTextField ->
            Box(
                Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant, shape)
                    .padding(paddingValues)
            ) { innerTextField() }
        }
    )
}

@Preview
@Composable
private fun PreviewTextField() {
    CameraReminderTheme {
        var value by remember { mutableStateOf("") }
        TextField(value = value, onValueChange = { value = it })
    }
}