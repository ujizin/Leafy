package br.com.devlucasyuji.domain.usecase.plant.implementation

import br.com.devlucasyuji.domain.model.Plant
import br.com.devlucasyuji.domain.repository.PlantRepository
import br.com.devlucasyuji.domain.usecase.plant.AddPlant

internal class AddPlantImpl(
    private val plantRepository: PlantRepository,
) : AddPlant {

    override fun invoke(plant: Plant) = plantRepository.insertPlant(plant)
}