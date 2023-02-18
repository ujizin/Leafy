package br.com.devlucasyuji.domain.usecase.plant.implementation

import br.com.devlucasyuji.domain.model.Plant
import br.com.devlucasyuji.domain.repository.PlantRepository
import br.com.devlucasyuji.domain.usecase.plant.AddDraftPlant

class AddDraftPlantImpl(
    private val plantRepository: PlantRepository,
) : AddDraftPlant {

    override fun invoke(plant: Plant) = plantRepository.updateDraftPlant(plant)
}