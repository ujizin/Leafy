package com.ujizin.leafy.domain.usecase.user.load

import com.ujizin.leafy.domain.model.User
import com.ujizin.leafy.domain.repository.UserRepository
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.result.asResult
import kotlinx.coroutines.flow.Flow

internal class LoadUserUseCaseImpl(private val repository: UserRepository) : LoadUserUseCase {

    override fun invoke(): Flow<Result<User>> = repository.getUser().asResult()
}
