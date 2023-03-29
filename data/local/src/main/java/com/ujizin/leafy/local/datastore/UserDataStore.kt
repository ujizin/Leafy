package com.ujizin.leafy.local.datastore

import com.ujizin.leafy.local.model.UserStore
import kotlinx.coroutines.flow.Flow

/**
 * Data store to User.
 * */
interface UserDataStore {

    /**
     * Get an user.
     *
     * @return an user
     * */
    fun getUser(): Flow<UserStore>

    /**
     * Update an user.
     *
     * @param user to be updated
     * */
    suspend fun updateUser(user: UserStore)
}
