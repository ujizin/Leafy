package com.ujizin.leafy.core.ui.components.modal

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ModalBottomSheet(
    showModal: Boolean,
    skipPartiallyExpanded: Boolean = false,
    onModalStateChanged: (Boolean) -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    val modalState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded,
    )

    LaunchedEffect(showModal) {
        if (showModal) modalState.show() else modalState.hide()
    }

    if (showModal) {
        androidx.compose.material3.ModalBottomSheet(
            sheetState = modalState,
            onDismissRequest = { onModalStateChanged(false) },
            content = content,
        )
    }
}
