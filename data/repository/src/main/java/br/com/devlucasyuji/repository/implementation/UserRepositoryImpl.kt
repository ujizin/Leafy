package br.com.devlucasyuji.repository.implementation

import br.com.devlucasyuji.domain.model.User
import br.com.devlucasyuji.domain.repository.UserRepository
import br.com.devlucasyuji.repository.datasource.UserDataSource
import br.com.devlucasyuji.repository.mapper.UserMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

internal class UserRepositoryImpl(
    private val datasource: UserDataSource,
    private val userMapper: UserMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserRepository {

    override fun getUser(): Flow<User> {
        TODO("Not yet implemented")
    }
}