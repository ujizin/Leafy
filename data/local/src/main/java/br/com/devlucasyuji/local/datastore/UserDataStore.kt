package br.com.devlucasyuji.local.datastore

import br.com.devlucasyuji.local.model.UserStore

/**
 * Data store to User.
 * */
interface UserDataStore {

    /**
     * Get an user.
     *
     * @return an user
     * */
    suspend fun getUser(): UserStore

    /**
     * Update an user.
     *
     * @param user to be updated
     * */
    suspend fun updateUser(user: UserStore)
}
