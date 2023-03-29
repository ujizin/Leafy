package com.ujizin.leafy.domain.usecase.plant.implementation

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.repository.PlantRepository
import com.ujizin.leafy.domain.usecase.plant.AddPlant

internal class AddPlantImpl(
    private val plantRepository: PlantRepository,
) : AddPlant {

    override fun invoke(plant: Plant) = plantRepository.insertPlant(plant)
}