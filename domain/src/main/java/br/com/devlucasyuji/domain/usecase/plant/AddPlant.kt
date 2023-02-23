package br.com.devlucasyuji.domain.usecase.plant

import br.com.devlucasyuji.domain.model.Plant
import kotlinx.coroutines.flow.Flow

/**
 * Use case to insert plant in the data source.
 * */
interface AddPlant {

    /**
     * Add a plant.
     *
     * @param plant plants to be added
     * */
    operator fun invoke(plant: Plant): Flow<Long>
}