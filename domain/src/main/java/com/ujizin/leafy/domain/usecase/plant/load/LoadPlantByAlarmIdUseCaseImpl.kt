package com.ujizin.leafy.domain.usecase.plant.load

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.result.mapResult
import com.ujizin.leafy.domain.usecase.alarm.load.LoadAlarmUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat

internal class LoadPlantByAlarmIdUseCaseImpl(
    private val loadAlarmUseCase: LoadAlarmUseCase,
    private val loadPlantUseCase: LoadPlantUseCase,
) : LoadPlantByAlarmIdUseCase {

    @OptIn(FlowPreview::class)
    override operator fun invoke(alarmId: Long): Flow<Result<Plant?>> = loadAlarmUseCase(alarmId)
        .mapResult()
        .flatMapConcat { loadPlantUseCase(it.plantId) }
}
