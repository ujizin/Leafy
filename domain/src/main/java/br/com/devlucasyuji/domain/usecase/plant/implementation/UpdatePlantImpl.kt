package br.com.devlucasyuji.domain.usecase.plant.implementation

import br.com.devlucasyuji.domain.model.Plant
import br.com.devlucasyuji.domain.repository.PlantRepository
import br.com.devlucasyuji.domain.usecase.plant.UpdatePlant
import kotlinx.coroutines.flow.Flow

internal class UpdatePlantImpl(
    private val plantRepository: PlantRepository,
) : UpdatePlant {

    override fun invoke(plant: Plant): Flow<Unit> = plantRepository.updatePlant(plant)

}
