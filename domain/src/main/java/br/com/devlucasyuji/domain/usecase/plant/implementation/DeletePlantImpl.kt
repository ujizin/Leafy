package br.com.devlucasyuji.domain.usecase.plant.implementation

import br.com.devlucasyuji.domain.model.Plant
import br.com.devlucasyuji.domain.repository.PlantRepository
import br.com.devlucasyuji.domain.usecase.plant.DeletePlant

internal class DeletePlantImpl(
    private val plantRepository: PlantRepository
): DeletePlant {

    override fun invoke(plant: Plant) = plantRepository.deletePlant(plant)

}
