package com.ujizin.leafy.features.plant

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ujizin.leafy.core.navigation.Args
import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.usecase.plant.LoadPlant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailPlantViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val loadPlant: LoadPlant
) : ViewModel() {

    private val plantId: Long = checkNotNull(savedStateHandle[Args.PlantId])

    private val _uiState = MutableStateFlow<DetailPlantUiState>(DetailPlantUiState.Initial)
    val uiState = _uiState.asStateFlow()

    fun getPlant() {
        loadPlant(plantId)
            .onEach(::update)
            .launchIn(viewModelScope)
    }

    private fun update(result: Result<Plant?>) = when (result) {
        is Result.Success -> _uiState.update {
            result.data?.let(DetailPlantUiState::Loaded) ?: DetailPlantUiState.NotFound
        }

        else -> Unit
    }
}

sealed interface DetailPlantUiState {

    object Initial : DetailPlantUiState

    data class Loaded(private val plant: Plant) : DetailPlantUiState

    object NotFound : DetailPlantUiState
}