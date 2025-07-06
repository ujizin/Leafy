package com.ujizin.leafy.alarm.usecase

import com.ujizin.leafy.alarm.scheduler.AlarmScheduler
import com.ujizin.leafy.core.ui.extensions.currentDay
import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.model.WeekDay
import com.ujizin.leafy.domain.result.mapResult
import com.ujizin.leafy.domain.usecase.alarm.load.LoadAlarmUseCase
import com.ujizin.leafy.domain.usecase.plant.load.LoadPlantUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach

/**
 * Schedule plant alarm use case
 * */
class SchedulePlantAlarmUseCase(
    private val loadPlantUseCase: LoadPlantUseCase,
    private val loadAlarmUseCase: LoadAlarmUseCase,
    private val alarmScheduler: AlarmScheduler,
    private val dispatcher: CoroutineDispatcher,
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(alarmId: Long, actualDay: WeekDay = currentDay) = loadAlarmUseCase(alarmId)
        .mapResult()
        .onEach { alarmScheduler.scheduleAlarm(it, actualDay) }
        .filter { alarm -> alarm.enabled && alarm.checkDayOfTheWeek() }
        .flatMapConcat { alarm -> loadPlantUseCase(alarm.plantId).mapResult() }
        .flowOn(dispatcher)

    private fun Alarm.checkDayOfTheWeek(): Boolean {
        return weekDays.contains(currentDay)
    }
}
