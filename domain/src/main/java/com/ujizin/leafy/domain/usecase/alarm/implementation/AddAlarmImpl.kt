package com.ujizin.leafy.domain.usecase.alarm.implementation

import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.repository.AlarmRepository
import com.ujizin.leafy.domain.usecase.alarm.AddAlarm

internal class AddAlarmImpl(
    private val alarmRepository: AlarmRepository,
) : AddAlarm {

    override fun invoke(alarm: Alarm) = alarmRepository.insertAlarm(alarm)
}
