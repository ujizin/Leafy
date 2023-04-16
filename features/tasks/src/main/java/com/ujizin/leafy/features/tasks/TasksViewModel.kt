package com.ujizin.leafy.features.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.model.WeekDay
import com.ujizin.leafy.domain.result.mapResult
import com.ujizin.leafy.domain.usecase.alarm.LoadAlarms
import com.ujizin.leafy.domain.usecase.plant.LoadPlant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
                /**
                 * TODO sort day by current day & add plant data on task.
                 * */
                val tasks = WeekDay.values().map { weekDay ->
                    val alarmList = alarms.filter { it.weekDays.contains(weekDay) }
                    Task(weekDay = weekDay, alarms = alarmList)
                }

                _uiState.update { TasksUiState.Success(tasks) }
            }
            .launchIn(viewModelScope)
    }
}

data class Task(
    val weekDay: WeekDay,
    val alarms: List<Alarm>,
)

sealed interface TasksUiState {
    object Initial : TasksUiState

    data class Success(val tasks: List<Task>) : TasksUiState
}
