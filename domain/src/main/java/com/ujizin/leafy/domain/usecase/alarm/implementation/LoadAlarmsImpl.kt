package com.ujizin.leafy.domain.usecase.alarm.implementation

import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.repository.AlarmRepository
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.result.asResult
import com.ujizin.leafy.domain.usecase.alarm.LoadAlarms
import kotlinx.coroutines.flow.Flow

class LoadAlarmsImpl(
    private val repository: AlarmRepository,
) : LoadAlarms {

    override fun invoke(
        plantId: Long,
    ): Flow<Result<List<Alarm>>> = if (plantId != LoadAlarms.INVALID_PLANT_ID) {
        repository.getAlarms(plantId).asResult()
    } else {
        repository.getAllAlarms().asResult()
    }
}
