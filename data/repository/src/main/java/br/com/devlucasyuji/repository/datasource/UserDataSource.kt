package br.com.devlucasyuji.repository.datasource

import br.com.devlucasyuji.repository.model.User

/**
 * Interface User to data source implementation.
 * */
interface UserDataSource {

    /**
     * Get an [User] from data source.
     *
     * @return an user
     * */
    suspend fun getUser(): User

    /**
     * Update [User].
     *
     * @param user user to be updated
     * */
    suspend fun updateUser(user: User)
}
