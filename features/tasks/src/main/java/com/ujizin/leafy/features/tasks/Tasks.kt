package com.ujizin.leafy.features.tasks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ujizin.leafy.core.ui.annotation.ThemePreviews
import com.ujizin.leafy.core.ui.components.Section

@Composable
fun TasksRoute(
    viewModel: TasksViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Tasks(
        uiState = uiState
    )
}

@Composable
private fun Tasks(
    uiState: TasksUiState
) {
    Box(Modifier.fillMaxSize()) {
        Section(title = "Alarm")
    }
}

@ThemePreviews
@Composable
private fun TasksPreview() {

}