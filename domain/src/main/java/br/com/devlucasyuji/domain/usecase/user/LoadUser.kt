package br.com.devlucasyuji.domain.usecase.user

import br.com.devlucasyuji.domain.model.User
import br.com.devlucasyuji.domain.result.Result
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
