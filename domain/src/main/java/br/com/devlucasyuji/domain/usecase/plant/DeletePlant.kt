package br.com.devlucasyuji.domain.usecase.plant

import br.com.devlucasyuji.domain.model.Plant
import kotlinx.coroutines.flow.Flow

/**
 * Use case to delete plant in the data source.
 * */
interface DeletePlant {

    /**
     * Delete a plant.
     *
     * @param plant plant to be deleted
     * */
    operator fun invoke(plant: Plant): Flow<Unit>
}