package br.com.devlucasyuji.domain.usecase.user

import br.com.devlucasyuji.domain.repository.UserRepository
import br.com.devlucasyuji.domain.usecase.user.implementation.LoadUserImpl
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
        repository: UserRepository
    ): LoadUser = LoadUserImpl(repository)

}