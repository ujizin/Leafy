package com.ujizin.leafy.domain.usecase.user

import com.ujizin.leafy.domain.model.User
import com.ujizin.leafy.domain.result.Result
import kotlinx.coroutines.flow.Flow

/**
 * Use case to load user in the data source.
 * */
interface LoadUser {

    /**
     *  Load an user.
     *
     *  @return an user
     * */
    operator fun invoke(): Flow<Result<User>>
}
