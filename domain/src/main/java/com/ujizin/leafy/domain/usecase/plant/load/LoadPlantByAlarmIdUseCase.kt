package com.ujizin.leafy.domain.usecase.plant.load

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.result.Result
import kotlinx.coroutines.flow.Flow

/**
 * Use case to load plant by alarm id from the data source.
 * */
interface LoadPlantByAlarmIdUseCase {

    /**
     * Load plant by alarm id.
     *
     * @param alarmId the alarm's id
     * @return the [Plant] associated with the given alarm id
     */
    operator fun invoke(alarmId: Long): Flow<Result<Plant?>>
}
