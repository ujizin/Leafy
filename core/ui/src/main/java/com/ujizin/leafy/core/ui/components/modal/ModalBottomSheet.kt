package com.ujizin.leafy.core.ui.components.modal

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ModalBottomSheet(
    showModal: Boolean,
    skipPartiallyExpanded: Boolean = false,
    onModalStateChanged: (Boolean) -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    val modalState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded,
    )
    var internalShowModal by remember(Unit) { mutableStateOf(true) }
    val currentValue by rememberUpdatedState(modalState.currentValue)
    val targetState by rememberUpdatedState(modalState.targetValue)

    // Fix bottom sheet animation to run accurately
    LaunchedEffect(Unit) { internalShowModal = showModal }

    LaunchedEffect(showModal) {
        if (showModal) modalState.show() else modalState.hide()
    }

    LaunchedEffect(currentValue, targetState) {
        internalShowModal = when {
            showModal -> true
            currentValue == targetState -> false
            else -> internalShowModal
        }
    }

    if (internalShowModal) {
        androidx.compose.material3.ModalBottomSheet(
            sheetState = modalState,
            onDismissRequest = { onModalStateChanged(false) },
            content = content,
        )
    }
}
