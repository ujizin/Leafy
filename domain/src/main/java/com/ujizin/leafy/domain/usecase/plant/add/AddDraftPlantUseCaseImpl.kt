package com.ujizin.leafy.domain.usecase.plant.add

import com.ujizin.leafy.domain.repository.PlantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.io.File

internal class AddDraftPlantUseCaseImpl(
    private val plantRepository: PlantRepository,
) : AddDraftPlantUseCase {

    override fun invoke(
        title: String?,
        file: File?,
        description: String?,
    ): Flow<Unit> = flow {
        val plant = plantRepository.getDraftPlant().first().let { plant ->
            plant.copy(
                title = title ?: plant.title,
                description = description ?: plant.description,
                file = file ?: plant.file,
            )
        }

        emit(plantRepository.updateDraftPlant(plant).first())
    }
}
