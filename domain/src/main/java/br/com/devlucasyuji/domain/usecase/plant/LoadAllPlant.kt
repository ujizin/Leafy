package br.com.devlucasyuji.domain.usecase.plant

import br.com.devlucasyuji.domain.model.Plant
import br.com.devlucasyuji.domain.result.Result
import kotlinx.coroutines.flow.Flow

/**
 * Use case to load all plant from repository.
 * */
interface LoadAllPlant {

    /**
     * Load all plant.
     *
     * @return list of all plant
     * */
    operator fun invoke(): Flow<Result<List<Plant>>>
}