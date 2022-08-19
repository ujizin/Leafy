package br.com.devlucasyuji.repository.model

/**
 * User model
 *
 * @param nickname nickname from user
 * @param createdAt date that was created
 * */
data class User(
    val nickname: String,
    val createdAt: String
)
