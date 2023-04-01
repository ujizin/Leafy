package com.ujizin.leafy.core.local.datasource

import com.ujizin.leafy.core.local.datastore.UserDataStore
import com.ujizin.leafy.core.local.mapper.UserMapper
import com.ujizin.leafy.core.repository.datasource.UserDataSource
import com.ujizin.leafy.core.repository.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserLocalDataSource(
    private val userDataStore: UserDataStore,
    private val userMapper: UserMapper,
) : UserDataSource {

    override fun getUser(): Flow<User> {
        return userDataStore.getUser().map(userMapper::toData)
    }

    override suspend fun updateUser(user: User) {
        userDataStore.updateUser(userMapper.toLocal(user))
    }
}
