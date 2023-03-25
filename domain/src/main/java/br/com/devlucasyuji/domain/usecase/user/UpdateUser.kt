package br.com.devlucasyuji.domain.usecase.user

import br.com.devlucasyuji.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Use case to update user in the data source.
 * */
interface UpdateUser {

    /**
     *  Update an user.
     *
     *  @return an user
     * */
    operator fun invoke(user: User): Flow<Unit>
}
