package br.com.devlucasyuji.domain.usecase.plant

import br.com.devlucasyuji.domain.model.Plant
import kotlinx.coroutines.flow.Flow

/**
 * Use case to insert draft plant in the data source.
 * */
interface AddDraftPlant {

    /**
     * Add draft plant.
     *
     * @param plant plant to be added|
     * */
    operator fun invoke(plant: Plant): Flow<Unit>
}
