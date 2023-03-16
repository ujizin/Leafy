package br.com.devlucasyuji.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.devlucasyuji.domain.model.Plant
import br.com.devlucasyuji.domain.result.Result
import br.com.devlucasyuji.domain.usecase.plant.FindPlant
import br.com.devlucasyuji.domain.usecase.plant.LoadAllPlant
import br.com.devlucasyuji.navigation.Args
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val loadPlants: LoadAllPlant,
    private val findPlant: FindPlant
) : ViewModel() {

    private val initAutoFocus: Boolean = checkNotNull(savedStateHandle[Args.SearchAutoFocus])

    private val _searchUiState = MutableStateFlow<SearchUiState>(SearchUiState.Initial(initAutoFocus))
    val searchUiState = _searchUiState.asStateFlow()

    fun search(sentence: String) {
        when {
            sentence.isBlank() -> loadPlants()
            else -> findPlant(sentence)
        }.onEachPlant().launchIn(viewModelScope)
    }

    private fun Flow<Result<List<Plant>>>.onEachPlant() = onEach { result ->
        _searchUiState.update { currentState ->
            when (result) {
                is Result.Error -> SearchUiState.Initial(initAutoFocus)
                Result.Loading -> currentState
                is Result.Success -> {
                    if (result.data.isEmpty()) {
                        SearchUiState.Empty
                    } else {
                        SearchUiState.Loaded(result.data)
                    }
                }
            }
        }
    }
}

sealed class SearchUiState(val items: List<Plant>) {
    data class Initial(val autoFocus: Boolean = false) : SearchUiState(emptyList())

    object Empty : SearchUiState(emptyList())

    data class Loaded(private val data: List<Plant>) : SearchUiState(data)
}