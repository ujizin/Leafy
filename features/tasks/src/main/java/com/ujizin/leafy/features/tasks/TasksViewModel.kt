package com.ujizin.leafy.features.tasks

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<TasksUiState>(TasksUiState.Initial)
    val uiState = _uiState.asStateFlow()

}

sealed interface TasksUiState {
    object Initial : TasksUiState
}