package br.com.devlucasyuji.publish.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.devlucasyuji.domain.usecase.plant.AddDraftPlant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PublishViewModel @Inject constructor(
    private val addDraftPlant: AddDraftPlant
) : ViewModel() {

    private val _uiState = MutableStateFlow<PublishUiState>(PublishUiState.Initial)
    val uiState: StateFlow<PublishUiState> = _uiState

    fun sendDraftPlant(title: String, description: String) {
        addDraftPlant(
            title = title,
            description = description
        ).onCompletion {
            _uiState.update { PublishUiState.Finish }
        }.launchIn(viewModelScope)
    }
}

sealed interface PublishUiState {
    object Initial : PublishUiState
    object Finish : PublishUiState
}