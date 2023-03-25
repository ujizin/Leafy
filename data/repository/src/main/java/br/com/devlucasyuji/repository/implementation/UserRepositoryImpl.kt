package br.com.devlucasyuji.repository.implementation

import br.com.devlucasyuji.domain.model.User
import br.com.devlucasyuji.domain.repository.UserRepository
import br.com.devlucasyuji.repository.datasource.UserDataSource
import br.com.devlucasyuji.repository.mapper.UserMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class UserRepositoryImpl(
    private val dataSource: UserDataSource,
    private val userMapper: UserMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserRepository {

    override fun getUser(): Flow<User> = dataSource.getUser()
        .map(userMapper::toDomain)
        .flowOn(dispatcher)

    override fun updateUser(user: User): Flow<Unit> = flow {
        emit(dataSource.updateUser(userMapper.toData(user)))
    }.flowOn(dispatcher)
}