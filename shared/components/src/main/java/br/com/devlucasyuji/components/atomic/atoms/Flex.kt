package br.com.devlucasyuji.components.atomic.atoms

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun ColumnScope.Flex() {
    Spacer(Modifier.weight(1F))
}

@Composable
internal fun RowScope.Flex() {
    Spacer(Modifier.weight(1F))
}