package br.com.devlucasyuji.repository.mapper

import br.com.devlucasyuji.domain.model.Theme
import br.com.devlucasyuji.domain.model.User
import br.com.devlucasyuji.repository.model.User as DataUser

/**
 * User mapper between domain and data modules.
 * */
internal class UserMapper {

    fun toDomain(user: DataUser): User = with(user) {
        User(
            nickname = nickname,
            settings = User.Settings(
                language = language,
                theme = Theme.valueOf(theme)
            )
        )
    }

    fun toData(user: User): DataUser = with(user) {
        DataUser(
            nickname = nickname,
            theme = settings.theme.toString(),
            language = settings.language
        )
    }
}
