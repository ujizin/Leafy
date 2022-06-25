package br.com.devlucasyuji.domain.usecase

import br.com.devlucasyuji.domain.repository.PhotoRepository
import br.com.devlucasyuji.domain.usecase.photo.LoadAllPhoto
import br.com.devlucasyuji.domain.usecase.photo.LoadAllPhotoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideLoadAllPhotos(
        repository: PhotoRepository
    ): LoadAllPhoto = LoadAllPhotoImpl(repository)
}