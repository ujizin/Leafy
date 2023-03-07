package br.com.devlucasyuji.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.devlucasyuji.domain.model.Plant
import br.com.devlucasyuji.domain.result.Result
import br.com.devlucasyuji.domain.usecase.plant.FindPlant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val findPlant: FindPlant
) : ViewModel() {

    private val _searchUiState = MutableStateFlow<SearchUiState>(SearchUiState.Initial)
    val searchUiState = _searchUiState.asStateFlow()

    fun search(sentence: String) {
        if (sentence.isBlank()) {
            _searchUiState.update { SearchUiState.Initial }
            return
        }

        findPlant(sentence)
            .onEach { result ->
                _searchUiState.update { currentState ->
                    when (result) {
                        is Result.Error -> SearchUiState.Initial
                        Result.Loading -> currentState
                        is Result.Success -> SearchUiState.Success(result.data)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

}

sealed interface SearchUiState {
    object Initial : SearchUiState

    data class Loaded(val data: List<Plant>) : SearchUiState

    data class Success(val data: List<Plant>) : SearchUiState
}