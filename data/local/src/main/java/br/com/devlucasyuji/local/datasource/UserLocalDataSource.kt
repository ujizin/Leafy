package br.com.devlucasyuji.local.datasource

import br.com.devlucasyuji.local.datastore.UserDataStore
import br.com.devlucasyuji.local.mapper.UserMapper
import br.com.devlucasyuji.repository.datasource.UserDataSource
import br.com.devlucasyuji.repository.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserLocalDataSource(
    private val userDataStore: UserDataStore,
    private val userMapper: UserMapper
) : UserDataSource {

    override fun getUser(): Flow<User> {
        return userDataStore.getUser().map(userMapper::toData)
    }

    override suspend fun updateUser(user: User) {
        userDataStore.updateUser(userMapper.toLocal(user))
    }
}