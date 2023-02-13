package br.com.devlucasyuji.components.ui.selector

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.themes.CameraReminderTheme

@Composable
fun Selector(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {
    Row(
        modifier = modifier.then(Modifier.clickable { /* TODO add bottomSheet */ }),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, style = MaterialTheme.typography.titleSmall)
        Row {
            Text(text = value)
            DownArrow(
                Modifier
                    .width(6.dp)
                    .height(3.dp)
                    .padding(start = 8.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun DownArrow(modifier: Modifier = Modifier, tint: Color = Color.Unspecified) {
    Canvas(modifier) {
        drawLine(
            color = tint,
            start = Offset(0F, 0F),
            end = Offset(size.width / 2F, size.height),
            strokeWidth = 2.dp.toPx(),
        )
        drawLine(tint, Offset(size.width / 2F, size.height), Offset(size.width, 0F), 2.dp.toPx())
    }
}

@Preview
@Composable
fun PreviewSelector() {
    CameraReminderTheme {
        Selector(
            modifier = Modifier.fillMaxWidth(),
            title = "ring",
            value = "rang"
        )
    }
}
