package com.ujizin.leafy.domain.usecase.alarm.implementation

import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.repository.AlarmRepository
import com.ujizin.leafy.domain.usecase.alarm.AddAlarm
import kotlinx.coroutines.flow.Flow

internal class AddAlarmImpl(
    private val alarmRepository: AlarmRepository,
) : AddAlarm {

    override fun invoke(alarm: Alarm): Flow<Unit> = alarmRepository.insertAlarm(alarm)
}
