package br.com.devlucasyuji.domain.usecase.plant.implementation

import br.com.devlucasyuji.domain.model.Plant
import br.com.devlucasyuji.domain.repository.PlantRepository
import br.com.devlucasyuji.domain.result.Result
import br.com.devlucasyuji.domain.result.asResult
import br.com.devlucasyuji.domain.usecase.plant.LoadAllPlant
import kotlinx.coroutines.flow.Flow

internal class LoadAllPlantImpl(private val repository: PlantRepository) : LoadAllPlant {

    override fun invoke(): Flow<Result<List<Plant>>> = repository.getPlants().asResult()
}
