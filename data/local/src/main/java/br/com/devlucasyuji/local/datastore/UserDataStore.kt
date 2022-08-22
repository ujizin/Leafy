package br.com.devlucasyuji.local.datastore

import br.com.devlucasyuji.local.model.User

/**
 * Data store to User.
 * */
interface UserDataStore {

    /**
     * Get an user.
     *
     * @return an user
     * */
    suspend fun getUser(): User

    /**
     * Update an user.
     *
     * @param user to be updated
     * */
    suspend fun updateUser(user: User)
}
