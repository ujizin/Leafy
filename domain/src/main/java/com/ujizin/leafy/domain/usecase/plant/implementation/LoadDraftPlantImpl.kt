package com.ujizin.leafy.domain.usecase.plant.implementation

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.repository.PlantRepository
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.result.asResult
import com.ujizin.leafy.domain.usecase.plant.LoadDraftPlant
import kotlinx.coroutines.flow.Flow

class LoadDraftPlantImpl(
    private val repository: PlantRepository,
) : LoadDraftPlant {

    override fun invoke(): Flow<Result<Plant>> = repository.getDraftPlant().asResult()
}
