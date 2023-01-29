package br.com.devlucasyuji.components.ui.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.extensions.capitalize
import br.com.devlucasyuji.components.ui.animated.animation.Animate.Animated
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.themes.CameraReminderTheme

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    value: String,
    singleLine: Boolean = true,
    shape: Shape = RoundedCornerShape(4.dp),
    paddingValues: PaddingValues = PaddingValues(16.dp),
    animation: Animation = Animation.SlideToTop,
    placeholder: String? = null,
    onValueChange: (String) -> Unit,
) {
    Animated(animation = animation) {
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
                ) {
                    innerTextField()
                    if (placeholder != null && value.isEmpty()) {
                        Text(text = placeholder.capitalize(), color = Color(0x7F3F4549))
                    }
                }
            }
        )
    }
}

@Preview
@Composable
private fun PreviewTextField() {
    CameraReminderTheme {
        var value by remember { mutableStateOf("") }
        TextField(value = value, onValueChange = { value = it })
    }
}