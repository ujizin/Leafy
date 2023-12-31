package com.ujizin.leafy.features.plant.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ujizin.leafy.core.navigation.Args
import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.result.mapResult
import com.ujizin.leafy.domain.usecase.plant.LoadPlant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PlantEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val loadPlant: LoadPlant,
) : ViewModel() {

    private val plantId: Long = checkNotNull(savedStateHandle[Args.PlantId])

    private val _uiState = MutableStateFlow<PlantEditUiState>(PlantEditUiState.Initial)
    val uiState get() = _uiState.asStateFlow()

    fun loadEditPlant() {
        loadPlant(plantId)
            .mapResult()
            .onEach { plant ->
                _uiState.update { PlantEditUiState.Success(plant) }
            }
            .launchIn(viewModelScope)
    }
}

sealed interface PlantEditUiState {
    data object Initial : PlantEditUiState

    data class Success(val plant: Plant) : PlantEditUiState
}
