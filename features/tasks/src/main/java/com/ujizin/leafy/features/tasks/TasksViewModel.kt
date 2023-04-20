package com.ujizin.leafy.features.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ujizin.leafy.core.ui.extensions.reorderByCurrentDay
import com.ujizin.leafy.domain.model.WeekDay
import com.ujizin.leafy.domain.result.mapResult
import com.ujizin.leafy.domain.usecase.alarm.LoadAlarms
import com.ujizin.leafy.domain.usecase.plant.LoadPlant
import com.ujizin.leafy.features.tasks.model.Task
import com.ujizin.leafy.features.tasks.model.TaskWeek
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val loadAllAlarms: LoadAlarms,
    private val loadPlant: LoadPlant,
) : ViewModel() {

    private val _uiState = MutableStateFlow<TasksUiState>(TasksUiState.Initial)
    val uiState = _uiState.asStateFlow()

    fun loadAlarms() {
        loadAllAlarms()
            .mapResult()
            .onEach { alarms ->
                val tasks = WeekDay.values().reorderByCurrentDay().map { weekDay ->
                    TaskWeek(
                        weekDay = weekDay,
                        tasks = alarms.filter {
                            it.weekDays.contains(weekDay)
                        }.map { alarm ->
                            Task(
                                plant = loadPlant(alarm.plantId).mapResult().first(),
                                alarm = alarm,
                            )
                        })
                }
                _uiState.update { TasksUiState.Success(tasks) }
            }
            .launchIn(viewModelScope)
    }
}


sealed interface TasksUiState {
    object Initial : TasksUiState

    data class Success(val tasks: List<TaskWeek>) : TasksUiState
}
