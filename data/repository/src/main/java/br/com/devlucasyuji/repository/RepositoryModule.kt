package br.com.devlucasyuji.repository

import br.com.devlucasyuji.domain.repository.PhotoRepository
import br.com.devlucasyuji.repository.datasource.PhotoDataSource
import br.com.devlucasyuji.repository.mapper.PhotoMapper
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
}