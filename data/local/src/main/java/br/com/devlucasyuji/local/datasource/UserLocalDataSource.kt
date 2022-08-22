package br.com.devlucasyuji.local.datasource

import br.com.devlucasyuji.local.datastore.UserDataStore
import br.com.devlucasyuji.local.mapper.UserMapper
import br.com.devlucasyuji.repository.datasource.UserDataSource
import br.com.devlucasyuji.repository.model.User

class UserLocalDataSource(
    private val userDataStore: UserDataStore,
    private val userMapper: UserMapper
) : UserDataSource {

    override suspend fun getUser(): User {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User) {
        TODO("Not yet implemented")
    }
}