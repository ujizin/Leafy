package br.com.devlucasyuji.repository

import br.com.devlucasyuji.domain.repository.FileRepository
import br.com.devlucasyuji.domain.repository.PhotoRepository
import br.com.devlucasyuji.domain.repository.UserRepository
import br.com.devlucasyuji.repository.datasource.PhotoDataSource
import br.com.devlucasyuji.repository.datasource.UserDataSource
import br.com.devlucasyuji.repository.implementation.FileRepositoryImpl
import br.com.devlucasyuji.repository.implementation.PhotoRepositoryImpl
import br.com.devlucasyuji.repository.implementation.UserRepositoryImpl
import br.com.devlucasyuji.repository.mapper.PhotoMapper
import br.com.devlucasyuji.repository.mapper.UserMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dependency injection of repository module
 * */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePhotoRepository(dataSource: PhotoDataSource): PhotoRepository = PhotoRepositoryImpl(
        dataSource = dataSource,
        mapper = PhotoMapper(),
    )

    @Provides
    @Singleton
    fun provideUserRepository(dataSource: UserDataSource): UserRepository = UserRepositoryImpl(
        dataSource = dataSource,
        userMapper = UserMapper()
    )

    @Provides
    @Singleton
    fun provideFileRepository(): FileRepository = FileRepositoryImpl()
}
