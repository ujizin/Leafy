package br.com.devlucasyuji.local.model

import kotlinx.serialization.Serializable

@Serializable
data class UserStore(
    val nickname: String,
    val theme: String? = null,
    val language: String? = null,
    val createdAt: String? = null
) {

    companion object {
        private const val DEFAULT_NICKNAME = "User"

        // TODO get a new Date library to use on created at
        fun default() = UserStore(
            nickname = DEFAULT_NICKNAME,
            theme = null,
            language = null,
            createdAt = " "
        )
    }
}

fun UserStore?.orDefault() = this ?: UserStore.default()