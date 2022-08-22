package br.com.devlucasyuji.repository.mapper

import br.com.devlucasyuji.repository.model.User as DataUser
import br.com.devlucasyuji.domain.model.User

/**
 * User mapper between domain and data modules.
 * */
internal class UserMapper {

    fun toDomain(user: DataUser): User = with(user) {
        User(nickname = nickname)
    }

    fun toData(user: User): DataUser = with(user) {
        DataUser(nickname = nickname)
    }
}
