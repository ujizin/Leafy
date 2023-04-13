package com.ujizin.leafy.domain.usecase.alarm.implementation

import com.ujizin.leafy.domain.repository.AlarmRepository
import com.ujizin.leafy.domain.result.asResult
import com.ujizin.leafy.domain.usecase.alarm.LoadAlarm

class LoadAlarmImpl(
    private val repository: AlarmRepository,
) : LoadAlarm {

    override fun invoke(id: Long) = repository.getAlarmById(id).asResult()
}
