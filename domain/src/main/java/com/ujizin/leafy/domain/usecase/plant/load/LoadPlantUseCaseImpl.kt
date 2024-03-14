package com.ujizin.leafy.domain.usecase.plant.load

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.repository.PlantRepository
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.result.asResult
import kotlinx.coroutines.flow.Flow

internal class LoadPlantUseCaseImpl(private val repository: PlantRepository) : LoadPlantUseCase {

    override fun invoke(id: Long): Flow<Result<Plant?>> = repository.getPlant(id).asResult()
}
