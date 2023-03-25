package br.com.devlucasyuji.local.mapper

import br.com.devlucasyuji.local.model.UserStore
import br.com.devlucasyuji.repository.model.User

/**
 * Mapper between repository and local modules.
 * */
class UserMapper {
    fun toData(user: UserStore) = with(user) {
        User(nickname, theme, language)
    }

    fun toLocal(user: User) = with(user) {
        UserStore(nickname, theme, language)
    }
}
