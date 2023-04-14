package com.ujizin.leafy.features.plant

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ujizin.leafy.core.navigation.Args
import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.result.mapResult
import com.ujizin.leafy.domain.usecase.alarm.DeleteAlarm
import com.ujizin.leafy.domain.usecase.alarm.LoadAlarms
import com.ujizin.leafy.domain.usecase.alarm.UpdateAlarm
import com.ujizin.leafy.domain.usecase.plant.DeletePlant
import com.ujizin.leafy.domain.usecase.plant.LoadPlant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailPlantViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    loadPlant: LoadPlant,
    loadAlarms: LoadAlarms,
    private val deletePlantUseCase: DeletePlant,
    private val deleteAlarmUseCase: DeleteAlarm,
    private val updateAlarmUseCase: UpdateAlarm,
) : ViewModel() {

    private val plantId: Long = checkNotNull(savedStateHandle[Args.PlantId])

    @OptIn(FlowPreview::class)
    val uiState = loadPlant(plantId)
        .mapResult()
        .flatMapConcat { plant ->
            loadAlarms(plant.id)
                .mapResult()
                .map { alarms ->
                    DetailPlantUiState.Loaded(plant, alarms)
                }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DetailPlantUiState.Initial,
        )

    fun updateAlarm(alarm: Alarm) {
        updateAlarmUseCase(alarm).launchIn(viewModelScope)
    }

    @OptIn(FlowPreview::class)
    fun deletePlant(plant: Plant, onCompletion: () -> Unit) {
        flowOf(
            deleteAlarmUseCase(plantId),
            deletePlantUseCase(plant)
        ).flattenMerge().onCompletion {
            onCompletion()
        }.launchIn(viewModelScope)
    }
}

sealed interface DetailPlantUiState {

    object Initial : DetailPlantUiState

    data class Loaded(
        val plant: Plant,
        val alarms: List<Alarm>,
    ) : DetailPlantUiState

    object NotFound : DetailPlantUiState
}
