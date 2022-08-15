package br.com.devlucasyuji.repository.implementation

import br.com.devlucasyuji.domain.model.User
import br.com.devlucasyuji.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl: UserRepository {

    override fun getUser(): Flow<User> {
        TODO("Not yet implemented")
    }
}