package br.com.devlucasyuji.domain.usecase

import br.com.devlucasyuji.domain.repository.PhotoRepository
import br.com.devlucasyuji.domain.repository.UserRepository
import br.com.devlucasyuji.domain.usecase.photo.AddPhoto
import br.com.devlucasyuji.domain.usecase.photo.DeletePhoto
import br.com.devlucasyuji.domain.usecase.photo.implementation.AddPhotoImpl
import br.com.devlucasyuji.domain.usecase.photo.LoadAllPhoto
import br.com.devlucasyuji.domain.usecase.photo.UpdatePhoto
import br.com.devlucasyuji.domain.usecase.photo.implementation.DeletePhotoImpl
import br.com.devlucasyuji.domain.usecase.photo.implementation.LoadAllPhotoImpl
import br.com.devlucasyuji.domain.usecase.photo.implementation.UpdatePhotoImpl
import br.com.devlucasyuji.domain.usecase.user.LoadUser
import br.com.devlucasyuji.domain.usecase.user.implementation.LoadUserImpl
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

    @Provides
    @Singleton
    fun provideAddPhoto(
        repository: PhotoRepository
    ): AddPhoto = AddPhotoImpl(repository)

    @Provides
    @Singleton
    fun provideUpdatePhoto(
        repository: PhotoRepository
    ): UpdatePhoto = UpdatePhotoImpl(repository)

    @Provides
    @Singleton
    fun provideDeletePhoto(
        repository: PhotoRepository
    ): DeletePhoto = DeletePhotoImpl(repository)

    @Provides
    @Singleton
    fun provideLoadUser(
        repository: UserRepository
    ): LoadUser = LoadUserImpl(repository)
}