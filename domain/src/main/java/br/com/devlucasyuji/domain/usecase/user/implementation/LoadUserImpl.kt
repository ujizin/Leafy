package br.com.devlucasyuji.domain.usecase.user.implementation

import br.com.devlucasyuji.domain.model.User
import br.com.devlucasyuji.domain.repository.UserRepository
import br.com.devlucasyuji.domain.result.Result
import br.com.devlucasyuji.domain.result.asResult
import br.com.devlucasyuji.domain.usecase.user.LoadUser
import kotlinx.coroutines.flow.Flow

class LoadUserImpl(private val repository: UserRepository) : LoadUser {

    override fun invoke(): Flow<Result<User>> = repository.getUser().asResult()

}
