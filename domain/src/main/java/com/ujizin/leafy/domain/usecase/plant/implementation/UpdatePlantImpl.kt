package com.ujizin.leafy.domain.usecase.plant.implementation

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.repository.PlantRepository
import com.ujizin.leafy.domain.usecase.plant.UpdatePlant
import kotlinx.coroutines.flow.Flow

internal class UpdatePlantImpl(
    private val plantRepository: PlantRepository,
) : UpdatePlant {

    override fun invoke(plant: Plant): Flow<Unit> = plantRepository.updatePlant(plant)
}
