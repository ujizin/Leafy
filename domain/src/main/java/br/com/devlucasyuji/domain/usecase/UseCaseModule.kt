package br.com.devlucasyuji.domain.usecase

import br.com.devlucasyuji.domain.repository.FileRepository
import br.com.devlucasyuji.domain.repository.PlantRepository
import br.com.devlucasyuji.domain.repository.UserRepository
import br.com.devlucasyuji.domain.usecase.file.SaveFile
import br.com.devlucasyuji.domain.usecase.file.implementation.SaveFileImpl
import br.com.devlucasyuji.domain.usecase.plant.AddPlant
import br.com.devlucasyuji.domain.usecase.plant.DeletePlant
import br.com.devlucasyuji.domain.usecase.plant.implementation.AddPlantImpl
import br.com.devlucasyuji.domain.usecase.plant.LoadAllPlant
import br.com.devlucasyuji.domain.usecase.plant.UpdatePlant
import br.com.devlucasyuji.domain.usecase.plant.implementation.DeletePlantImpl
import br.com.devlucasyuji.domain.usecase.plant.implementation.LoadAllPlantImpl
import br.com.devlucasyuji.domain.usecase.plant.implementation.UpdatePlantImpl
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
    fun provideLoadAllPlants(
        repository: PlantRepository
    ): LoadAllPlant = LoadAllPlantImpl(repository)

    @Provides
    @Singleton
    fun provideAddPlant(
        repository: PlantRepository
    ): AddPlant = AddPlantImpl(repository)

    @Provides
    @Singleton
    fun provideUpdatePlant(
        repository: PlantRepository
    ): UpdatePlant = UpdatePlantImpl(repository)

    @Provides
    @Singleton
    fun provideDeletePlant(
        repository: PlantRepository
    ): DeletePlant = DeletePlantImpl(repository)

    @Provides
    @Singleton
    fun provideLoadUser(
        repository: UserRepository
    ): LoadUser = LoadUserImpl(repository)

    @Provides
    @Singleton
    fun provideSaveFile(
        repository: FileRepository,
    ): SaveFile = SaveFileImpl(repository)
}