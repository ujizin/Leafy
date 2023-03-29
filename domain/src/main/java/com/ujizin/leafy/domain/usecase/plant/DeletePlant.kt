package com.ujizin.leafy.domain.usecase.plant

import com.ujizin.leafy.domain.model.Plant
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