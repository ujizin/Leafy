package br.com.devlucasyuji.alarm.alarm

import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.devlucasyuji.alarm.AlarmScheduler
import br.com.devlucasyuji.alarm.model.RepeatMode
import br.com.devlucasyuji.alarm.receiver.AlarmReceiver
import br.com.devlucasyuji.domain.model.Alarm
import br.com.devlucasyuji.domain.model.Ringtone
import br.com.devlucasyuji.domain.result.Result
import br.com.devlucasyuji.domain.usecase.alarm.AddAlarm
import br.com.devlucasyuji.domain.usecase.plant.AddPlant
import br.com.devlucasyuji.domain.usecase.plant.LoadDraftPlant
import br.com.devlucasyuji.domain.usecase.ringtone.LoadRingtones
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val loadDraftPlant: LoadDraftPlant,
    private val addPlant: AddPlant,
    private val addAlarm: AddAlarm,
    private val alarmScheduler: AlarmScheduler,
    private val getRingtones: LoadRingtones
) : ViewModel() {

    private val _uiState = MutableStateFlow<AlarmUiState>(AlarmUiState.Initial)
    val uiState: StateFlow<AlarmUiState> = _uiState

    fun setupRingtones() {
        getRingtones()
            .onEach { ringtones -> _uiState.update { AlarmUiState.Initialized(ringtones.distinctBy { it.title }) } }
            .launchIn(viewModelScope)
    }

    @OptIn(FlowPreview::class)
    fun addPlantWithAlarm(
        ringtone: Ringtone,
        repeatMode: RepeatMode,
        hours: Int,
        minutes: Int,
        onPlantPublished: () -> Unit,
    ) {
        loadDraftPlant()
            .mapNotNull { result ->
                when (result) {
                    is Result.Error -> throw Exception(result.exception)
                    is Result.Success -> result.data.copy(id = 0)
                    else -> null
                }
            }.flatMapConcat { plant ->
                addPlant(plant).flatMapConcat { id ->
                    addAlarm(
                        Alarm(
                            plantId = id,
                            ringtoneUri = ringtone.uri,
                            repeatIntervalInMillis = repeatMode.intervalTimeMillis,
                            minutes = minutes,
                            hours = hours,
                        )
                    ).onEach {
                        alarmScheduler.scheduleAlarm(
                            hours = hours,
                            minutes = minutes,
                            ringtoneUri = ringtone.uri,
                            intervalInMillis = repeatMode.intervalTimeMillis,
                            bundle = bundleOf(AlarmReceiver.ALARM_PLANT_ID_EXTRA to id)
                        )
                    }
                }
            }
            .onCompletion { onPlantPublished() }
            .catch {
                // Handle error
            }.launchIn(viewModelScope)
    }
}

sealed interface AlarmUiState {
    object Initial : AlarmUiState

    data class Initialized(val ringtones: List<Ringtone>) : AlarmUiState
}