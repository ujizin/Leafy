package com.ujizin.leafy.domain.usecase.user

import com.ujizin.leafy.domain.repository.UserRepository
import com.ujizin.leafy.domain.usecase.user.implementation.LoadUserImpl
import com.ujizin.leafy.domain.usecase.user.implementation.UpdateUserImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun provideLoadUser(
        repository: UserRepository,
    ): LoadUser = LoadUserImpl(repository)

    @Provides
    @Singleton
    fun provideUpdateUser(
        repository: UserRepository,
    ): UpdateUser = UpdateUserImpl(repository)
}
