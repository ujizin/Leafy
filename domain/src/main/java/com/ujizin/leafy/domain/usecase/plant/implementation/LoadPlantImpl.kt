package com.ujizin.leafy.domain.usecase.plant.implementation

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.repository.PlantRepository
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.result.asResult
import com.ujizin.leafy.domain.usecase.plant.LoadPlant
import kotlinx.coroutines.flow.Flow

class LoadPlantImpl(private val repository: PlantRepository) : LoadPlant {

    override fun invoke(id: Long): Flow<Result<Plant?>> = repository.getPlant(id).asResult()
}
