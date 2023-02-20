package br.com.devlucasyuji.domain.usecase.plant.implementation

import br.com.devlucasyuji.domain.repository.PlantRepository
import br.com.devlucasyuji.domain.usecase.plant.AddDraftPlant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.io.File

internal class AddDraftPlantImpl(
    private val plantRepository: PlantRepository,
) : AddDraftPlant {

    override fun invoke(
        title: String?,
        file: File?,
        description: String?,
    ): Flow<Unit> = flow {
        val plant = plantRepository.getDraftPlant().first().let { plant ->
            plant.copy(
                title = title ?: plant.title,
                description = description ?: plant.description
            )
        }

        emit(plantRepository.updateDraftPlant(plant).first())
    }
}