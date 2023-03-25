package br.com.devlucasyuji.domain.model

import java.util.Locale

/**
 * User model
 *
 * @param nickname nickname from user
 * */
data class User(
    val nickname: String,
    val settings: Settings,
) {

    data class Settings(
        val theme: Theme,
        val language: String,
    )

    companion object {
        val default: User
            get() = User(
                nickname = "User",
                settings = Settings(
                    theme = Theme.System,
                    language = Locale.getDefault().displayLanguage
                )
            )
    }
}
