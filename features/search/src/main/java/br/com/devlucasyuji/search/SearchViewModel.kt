package br.com.devlucasyuji.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.devlucasyuji.domain.model.Plant
import br.com.devlucasyuji.domain.result.Result
import br.com.devlucasyuji.domain.usecase.plant.FindPlant
import br.com.devlucasyuji.domain.usecase.plant.LoadAllPlant
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
    private val loadPlants: LoadAllPlant,
    private val findPlant: FindPlant
) : ViewModel() {

    private val _searchUiState = MutableStateFlow<SearchUiState>(SearchUiState.Initial)
    val searchUiState = _searchUiState.asStateFlow()

    fun getPlants() {
        loadPlants().onEachPlant().launchIn(viewModelScope)
    }

    fun search(sentence: String) {
        if (sentence.isBlank()) return getPlants()

        findPlant(sentence).onEachPlant().launchIn(viewModelScope)
    }

    private fun Flow<Result<List<Plant>>>.onEachPlant() = onEach { result ->
        _searchUiState.update { currentState ->
            when (result) {
                is Result.Error -> SearchUiState.Initial
                Result.Loading -> currentState
                is Result.Success -> SearchUiState.Loaded(result.data)
            }
        }
    }
}

sealed class SearchUiState(val items: List<Plant>) {
    object Initial : SearchUiState(emptyList())

    object Empty : SearchUiState(emptyList())

    data class Loaded(private val data: List<Plant>) : SearchUiState(data)
}