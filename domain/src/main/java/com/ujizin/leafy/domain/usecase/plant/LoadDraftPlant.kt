package com.ujizin.leafy.domain.usecase.plant

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.result.Result
import kotlinx.coroutines.flow.Flow

/**
 * Use case to insert draft plant in the data source.
 * */
interface LoadDraftPlant {

    /**
     * Get draft plant.
     * */
    operator fun invoke(): Flow<Result<Plant>>
}