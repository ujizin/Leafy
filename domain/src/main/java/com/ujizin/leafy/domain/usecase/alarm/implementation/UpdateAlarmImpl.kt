package com.ujizin.leafy.domain.usecase.alarm.implementation

import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.repository.AlarmRepository
import com.ujizin.leafy.domain.usecase.alarm.UpdateAlarm

class UpdateAlarmImpl(
    private val repository: AlarmRepository,
) : UpdateAlarm {

    override fun invoke(alarm: Alarm) = repository.updateAlarm(alarm)
}
