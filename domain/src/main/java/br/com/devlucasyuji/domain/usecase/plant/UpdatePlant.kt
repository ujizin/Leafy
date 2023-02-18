package br.com.devlucasyuji.domain.usecase.plant

import br.com.devlucasyuji.domain.model.Plant
import kotlinx.coroutines.flow.Flow

/**
 * Use case to update plant in the data source.
 * */
interface UpdatePlant {

    /**
     * Update a plant.
     *
     * @param plant plant to be updated
     * */
    operator fun invoke(plant: Plant): Flow<Unit>
}