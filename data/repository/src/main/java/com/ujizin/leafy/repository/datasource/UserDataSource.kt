package com.ujizin.leafy.repository.datasource

import com.ujizin.leafy.repository.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Interface User to data source implementation.
 * */
interface UserDataSource {

    /**
     * Get an [User] from data source.
     *
     * @return an user
     * */
    fun getUser(): Flow<User>

    /**
     * Update [User].
     *
     * @param user user to be updated
     * */
    suspend fun updateUser(user: User)
}
