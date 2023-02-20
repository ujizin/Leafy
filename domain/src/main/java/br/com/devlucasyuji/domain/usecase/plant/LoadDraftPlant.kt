package br.com.devlucasyuji.domain.usecase.plant

import br.com.devlucasyuji.domain.model.Plant
import br.com.devlucasyuji.domain.result.Result
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