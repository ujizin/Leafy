package br.com.devlucasyuji.components.ui.selector

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.extensions.capitalize
import br.com.devlucasyuji.themes.CameraReminderTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Selector(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    value: String? = null,
    showModal: Boolean = false,
    onModalStateChanged: (Boolean) -> Unit = {},
    content: @Composable ColumnScope.() -> Unit,
) {
    val modalState = rememberModalBottomSheetState()
    var internalShowModal by remember { mutableStateOf(showModal) }

    LaunchedEffect(showModal) {
        if (showModal) modalState.show() else modalState.hide()

        internalShowModal = showModal
    }

    if (internalShowModal) {
        ModalBottomSheet(
            sheetState = modalState,
            onDismissRequest = {
                internalShowModal = false
                onModalStateChanged(false)
            },
            content = content,
        )
    }

    Row(
        modifier = Modifier
            .clickable(onClick = {
                internalShowModal = true
                onModalStateChanged(true)
            })
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(text = title.capitalize(), style = MaterialTheme.typography.titleSmall)
            if (subTitle != null) {
                Spacer(Modifier.height(12.dp))
                Text(text = subTitle.capitalize())
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (value != null) {
                Text(text = value.capitalize())
            }
            DownArrow(
                Modifier
                    .padding(start = 8.dp)
                    .width(16.dp)
                    .height(8.dp),
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

@Preview
@Composable
fun PreviewSelector() {
    CameraReminderTheme {
        Selector(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            title = "ring",
            value = "rang",
            content = {
                Text("Foo")
            }
        )
    }
}

@Preview
@Composable
fun PreviewSelectorWithSubtitle() {
    CameraReminderTheme {
        Selector(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            title = "ring",
            subTitle = "rong",
            value = "rang",
            content = {
                Text("Foo")
            }
        )
    }
}

@Preview
@Composable
fun PreviewSelectorWithNoValue() {
    CameraReminderTheme {
        Selector(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            title = "ring",
            subTitle = "rong",
            content = {
                Text("Foo")
            }
        )
    }
}
