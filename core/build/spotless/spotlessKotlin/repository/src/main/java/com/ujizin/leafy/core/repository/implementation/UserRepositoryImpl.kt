package com.ujizin.leafy.core.repository.implementation

import com.ujizin.leafy.core.repository.datasource.UserDataSource
import com.ujizin.leafy.core.repository.mapper.UserMapper
import com.ujizin.leafy.domain.model.User
import com.ujizin.leafy.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class UserRepositoryImpl(
    private val dataSource: UserDataSource,
    private val userMapper: UserMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : UserRepository {

    override fun getUser(): Flow<User> = dataSource.getUser()
        .map(userMapper::toDomain)
        .flowOn(dispatcher)

    override fun updateUser(user: User): Flow<Unit> = flow {
        emit(dataSource.updateUser(userMapper.toData(user)))
    }.flowOn(dispatcher)
}
