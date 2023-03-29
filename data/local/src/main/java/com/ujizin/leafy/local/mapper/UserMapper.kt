package com.ujizin.leafy.local.mapper

import com.ujizin.leafy.local.model.UserStore
import com.ujizin.leafy.repository.model.User

/**
 * Mapper between repository and local modules.
 * */
class UserMapper {
    fun toData(user: UserStore) = with(user) {
        User(nickname, theme, language, dynamicColor)
    }

    fun toLocal(user: User) = with(user) {
        UserStore(nickname, theme, language, dynamicColor)
    }
}
