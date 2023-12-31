package com.ujizin.leafy.home

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.result.mapResult
import com.ujizin.leafy.domain.usecase.plant.LoadAllPlant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadAllPlant: LoadAllPlant,
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUIState>(HomeUIState.Loading)
    val uiState = _uiState.asStateFlow()
    fun loadHome() {
        loadAllPlant()
            .mapResult()
            .catch { throwable -> _uiState.update { HomeUIState.Error(throwable) } }
            .onEach { plants -> _uiState.update { HomeUIState.Success(plants) } }
            .launchIn(viewModelScope)
    }
}

sealed interface HomeUIState {
    @Immutable
    data class Success(val plants: List<Plant>) : HomeUIState

    @Immutable
    data class Error(val throwable: Throwable?) : HomeUIState

    @Immutable
    data object Loading : HomeUIState
}
