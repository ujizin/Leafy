package br.com.devlucasyuji.domain.usecase.plant.implementation

import br.com.devlucasyuji.domain.model.Plant
import br.com.devlucasyuji.domain.repository.PlantRepository
import br.com.devlucasyuji.domain.result.Result
import br.com.devlucasyuji.domain.result.asResult
import br.com.devlucasyuji.domain.usecase.plant.LoadPlant
import kotlinx.coroutines.flow.Flow

class LoadPlantImpl(private val repository: PlantRepository): LoadPlant {

    override fun invoke(id: Long): Flow<Result<Plant?>> = repository.getPlant(id).asResult()
}