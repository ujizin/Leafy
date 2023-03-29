package com.ujizin.leafy.domain.usecase.user.implementation

import com.ujizin.leafy.domain.model.User
import com.ujizin.leafy.domain.repository.UserRepository
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.result.asResult
import com.ujizin.leafy.domain.usecase.user.LoadUser
import kotlinx.coroutines.flow.Flow

internal class LoadUserImpl(private val repository: UserRepository) : LoadUser {

    override fun invoke(): Flow<Result<User>> = repository.getUser().asResult()

}
