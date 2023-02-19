package br.com.devlucasyuji.domain.usecase.file

import br.com.devlucasyuji.domain.repository.FileRepository
import br.com.devlucasyuji.domain.usecase.file.implementation.SaveFileImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FileModule {

    @Provides
    @Singleton
    fun provideSaveFile(
        repository: FileRepository,
    ): SaveFile = SaveFileImpl(repository)
}