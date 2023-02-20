package br.com.devlucasyuji.domain.usecase.plant.implementation

import br.com.devlucasyuji.domain.model.Plant
import br.com.devlucasyuji.domain.repository.PlantRepository
import br.com.devlucasyuji.domain.result.Result
import br.com.devlucasyuji.domain.result.asResult
import br.com.devlucasyuji.domain.usecase.plant.LoadDraftPlant
import kotlinx.coroutines.flow.Flow

class LoadDraftPlantImpl(
    private val repository: PlantRepository
) : LoadDraftPlant {

    override fun invoke(): Flow<Result<Plant>> = repository.getDraftPlant().asResult()
}
