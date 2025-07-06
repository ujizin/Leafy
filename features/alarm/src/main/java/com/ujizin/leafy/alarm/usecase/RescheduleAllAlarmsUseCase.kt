package com.ujizin.leafy.alarm.usecase

import com.ujizin.leafy.alarm.scheduler.AlarmScheduler
import com.ujizin.leafy.domain.result.mapResult
import com.ujizin.leafy.domain.usecase.alarm.load.LoadAlarmsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RescheduleAllAlarmsUseCase @Inject constructor(
    private val loadAlarmsUseCase: LoadAlarmsUseCase,
    private val alarmScheduler: AlarmScheduler,
    private val dispatcher: CoroutineDispatcher,
) {

    operator fun invoke() = loadAlarmsUseCase()
        .mapResult()
        .onEach { alarms -> alarms.forEach(alarmScheduler::scheduleAlarm) }
        .flowOn(dispatcher)
}
