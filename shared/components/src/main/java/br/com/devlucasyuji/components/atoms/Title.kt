package br.com.devlucasyuji.components.atoms

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun Title(
    text: String,
    modifier: Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = MaterialTheme.typography.titleLarge.fontSize,
        color = MaterialTheme.colorScheme.onPrimary,
    )
}