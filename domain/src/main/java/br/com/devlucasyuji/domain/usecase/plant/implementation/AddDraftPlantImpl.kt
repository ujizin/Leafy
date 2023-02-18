package br.com.devlucasyuji.domain.usecase.plant.implementation

import br.com.devlucasyuji.domain.model.orCreateDraft
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
        date: String?,
        file: File?,
        description: String?,
    ): Flow<Unit> = flow {
        val plant = plantRepository.getDraftPlant().first().orCreateDraft().let { plant ->
            plant.copy(
                title = title ?: plant.title,
                date = date ?: plant.date,
                description = description ?: plant.description
            )
        }

        emit(plantRepository.updateDraftPlant(plant).first())
    }
}