package br.com.devlucasyuji.domain.usecase.user.implementation

import br.com.devlucasyuji.domain.model.User
import br.com.devlucasyuji.domain.repository.UserRepository
import br.com.devlucasyuji.domain.usecase.user.UpdateUser

class UpdateUserImpl(
    private val userRepository: UserRepository
) : UpdateUser {

    override fun invoke(user: User) = userRepository.updateUser(user)
}