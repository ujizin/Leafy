package br.com.devlucasyuji.domain.usecase.plant

import br.com.devlucasyuji.domain.model.Plant
import br.com.devlucasyuji.domain.result.Result
import kotlinx.coroutines.flow.Flow


/**
 * Find plant use case.
 * */
interface FindPlant {

    /**
     * Find plant by sentence.
     *
     * @param sentence sentence to find plant in title or description.
     * */
    operator fun invoke(sentence: String): Flow<Result<List<Plant>>>
}
