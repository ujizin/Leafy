package br.com.devlucasyuji.components.ui.selector

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ModalSelector(
    modifier: Modifier = Modifier,
    title: String,
    currentValue: String,
    values: List<String>,
    onValueChanged: (String) -> Unit,
) {
    Column(modifier) {
        Text(
            modifier = Modifier.padding(start = 20.dp, top = 32.dp, end = 20.dp),
            text = title, style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        values.forEach { text ->
            ModalItemSelector(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                text = text,
                selected = currentValue == text
            ) {
                onValueChanged(text)
            }
        }
    }
}

@Composable
fun ModalItemSelector(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onItemSelectorClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onItemSelectorClicked)
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = text)
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary, CircleShape)
                .size(24.dp)
                .padding(8.dp)
                .then(
                    when {
                        selected -> Modifier.background(
                            MaterialTheme.colorScheme.onPrimary,
                            CircleShape
                        )

                        else -> Modifier
                    }
                )
        )
    }
}