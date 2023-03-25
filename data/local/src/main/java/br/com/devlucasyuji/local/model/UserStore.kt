package br.com.devlucasyuji.local.model

import kotlinx.serialization.Serializable
import java.util.Locale

@Serializable
data class UserStore(
    val nickname: String,
    val theme: String,
    val language: String,
    val createdAt: String? = null
) {

    companion object {
        private const val DEFAULT_NICKNAME = "User"

        // TODO get a new Date library to use on created at
        fun default() = UserStore(
            nickname = DEFAULT_NICKNAME,
            theme = "System",
            language = Locale.getDefault().displayLanguage.orEmpty(),
            createdAt = " "
        )
    }
}

fun UserStore?.orDefault() = this ?: UserStore.default()