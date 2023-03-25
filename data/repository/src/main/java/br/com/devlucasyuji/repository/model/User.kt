package br.com.devlucasyuji.repository.model

/**
 * User model
 *
 * @param nickname nickname from user
 * */
data class User(
    val nickname: String,
    val theme: String,
    val language: String,
)
