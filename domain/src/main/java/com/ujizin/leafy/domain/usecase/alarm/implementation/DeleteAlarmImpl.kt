package com.ujizin.leafy.domain.usecase.alarm.implementation

import com.ujizin.leafy.domain.repository.AlarmRepository
import com.ujizin.leafy.domain.usecase.alarm.DeleteAlarm

class DeleteAlarmImpl(
    private val repository: AlarmRepository,
) : DeleteAlarm {

    override fun invoke(plantId: Long) = repository.deleteAlarmByPlantId(plantId)
}
