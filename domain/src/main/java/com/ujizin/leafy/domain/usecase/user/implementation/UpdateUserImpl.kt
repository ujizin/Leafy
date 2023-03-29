package com.ujizin.leafy.domain.usecase.user.implementation

import com.ujizin.leafy.domain.model.User
import com.ujizin.leafy.domain.repository.UserRepository
import com.ujizin.leafy.domain.usecase.user.UpdateUser

class UpdateUserImpl(
    private val userRepository: UserRepository
) : UpdateUser {

    override fun invoke(user: User) = userRepository.updateUser(user)
}