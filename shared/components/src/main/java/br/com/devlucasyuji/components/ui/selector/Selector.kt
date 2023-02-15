package br.com.devlucasyuji.components.ui.selector

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.extensions.capitalize
import br.com.devlucasyuji.themes.CameraReminderTheme

@Composable
fun Selector(
    modifier: Modifier = Modifier,
    title: String,
    currentValue: String,
    values: List<String>,
    onSelectorClicked: OnClick,
) {
    LaunchedEffect(currentValue) {
        check(values.contains(currentValue)) { "current value must to be on values" }
    }
    RowItemSelector(
        modifier = modifier,
        title = title,
        currentValue = currentValue,
        onSelectorClicked = onSelectorClicked
    )
}

@Composable
fun RowItemSelector(
    modifier: Modifier = Modifier,
    title: String,
    currentValue: String,
    onSelectorClicked: () -> Unit,
) {
    Row(
        modifier = Modifier.clickable(onClick = onSelectorClicked).then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = title.capitalize(), style = MaterialTheme.typography.titleSmall)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = currentValue.capitalize())
            DownArrow(
                Modifier
                    .width(16.dp)
                    .height(4.dp)
                    .padding(start = 8.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun DownArrow(
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 2.dp,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Canvas(modifier) {
        val middleX = size.width / 2F
        val path = Path()
        with(path) {
            moveTo(0F, 0F)
            lineTo(middleX, size.height)
            lineTo(size.width, 0F)
        }

        drawPath(path, color = color, style = Stroke(strokeWidth.toPx()))

    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewSelector() {
    CameraReminderTheme {
        Selector(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            title = "ring",
            currentValue = "rang",
            values = listOf(),
            onSelectorClicked = {}
        )
    }
}
