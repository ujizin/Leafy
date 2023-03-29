package com.ujizin.leafy.domain.usecase.plant.implementation

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.repository.PlantRepository
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.result.asResult
import com.ujizin.leafy.domain.usecase.plant.LoadAllPlant
import kotlinx.coroutines.flow.Flow

internal class LoadAllPlantImpl(private val repository: PlantRepository) : LoadAllPlant {

    override fun invoke(): Flow<Result<List<Plant>>> = repository.getPlants().asResult()
}
