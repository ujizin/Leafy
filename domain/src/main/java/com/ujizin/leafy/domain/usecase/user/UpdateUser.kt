package com.ujizin.leafy.domain.usecase.user

import com.ujizin.leafy.domain.model.User
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
