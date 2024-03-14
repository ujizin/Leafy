package com.ujizin.leafy.alarm.usecase

import com.ujizin.leafy.alarm.scheduler.AlarmScheduler
import com.ujizin.leafy.core.ui.extensions.currentDay
import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.result.mapResult
import com.ujizin.leafy.domain.usecase.alarm.load.LoadAlarmUseCase
import com.ujizin.leafy.domain.usecase.plant.load.LoadPlantUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.onEach

/**
 * Schedule plant alarm use case
 * */
class SchedulePlantAlarm(
    private val loadPlant: LoadPlantUseCase,
    private val loadAlarm: LoadAlarmUseCase,
    private val alarmScheduler: AlarmScheduler,
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(alarmId: Long) = loadAlarm(alarmId)
        .mapResult()
        .onEach { alarmScheduler.scheduleAlarm(it) }
        .flatMapConcat { alarm ->
            if (!alarm.enabled || !alarm.checkDayOfTheWeek()) return@flatMapConcat emptyFlow()
            loadPlant(alarm.plantId).mapResult()
        }

    private fun Alarm.checkDayOfTheWeek(): Boolean {
        return weekDays.contains(currentDay)
    }
}
