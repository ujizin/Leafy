package br.com.devlucasyuji.domain.usecase.plant

import br.com.devlucasyuji.domain.model.Plant
import br.com.devlucasyuji.domain.result.Result
import kotlinx.coroutines.flow.Flow

/**
 * Load plant use case
 * */
interface LoadPlant {

    /**
     * Load plant by id.
     *
     * @param id the plant's id
     * */
    operator fun invoke(id: Long): Flow<Result<Plant?>>
}
