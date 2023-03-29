package com.ujizin.leafy.domain.usecase.plant.implementation

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.repository.PlantRepository
import com.ujizin.leafy.domain.usecase.plant.DeletePlant

internal class DeletePlantImpl(
    private val plantRepository: PlantRepository
) : DeletePlant {

    override fun invoke(plant: Plant) = plantRepository.deletePlant(plant)

}
