package br.com.devlucasyuji.local.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val nickname: String,
    val createdAt: String
) {

    companion object {
        private const val DEFAULT_NICKNAME = "User"

        // TODO get a new Date library to use on created at
        fun default() = User(
            nickname = DEFAULT_NICKNAME,
            createdAt = " "
        )
    }
}
