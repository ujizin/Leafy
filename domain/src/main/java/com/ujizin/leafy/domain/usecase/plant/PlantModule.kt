package com.ujizin.leafy.domain.usecase.plant

import com.ujizin.leafy.domain.repository.PlantRepository
import com.ujizin.leafy.domain.usecase.plant.implementation.AddDraftPlantImpl
import com.ujizin.leafy.domain.usecase.plant.implementation.AddPlantImpl
import com.ujizin.leafy.domain.usecase.plant.implementation.DeletePlantImpl
import com.ujizin.leafy.domain.usecase.plant.implementation.FindPlantImpl
import com.ujizin.leafy.domain.usecase.plant.implementation.LoadAllPlantImpl
import com.ujizin.leafy.domain.usecase.plant.implementation.LoadDraftPlantImpl
import com.ujizin.leafy.domain.usecase.plant.implementation.LoadPlantImpl
import com.ujizin.leafy.domain.usecase.plant.implementation.UpdatePlantImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlantModule {

    @Provides
    @Singleton
    fun provideLoadAllPlants(
        repository: PlantRepository,
    ): LoadAllPlant = LoadAllPlantImpl(repository)

    @Provides
    @Singleton
    fun provideLoadPlant(
        repository: PlantRepository,
    ): LoadPlant = LoadPlantImpl(repository)

    @Provides
    @Singleton
    fun provideAddPlant(
        repository: PlantRepository,
    ): AddPlant = AddPlantImpl(repository)

    @Provides
    @Singleton
    fun provideFindPlant(
        repository: PlantRepository,
    ): FindPlant = FindPlantImpl(repository)

    @Provides
    @Singleton
    fun provideUpdatePlant(
        repository: PlantRepository,
    ): UpdatePlant = UpdatePlantImpl(repository)

    @Provides
    @Singleton
    fun provideDeletePlant(
        repository: PlantRepository,
    ): DeletePlant = DeletePlantImpl(repository)

    @Provides
    @Singleton
    fun provideAddDraftPlant(
        repository: PlantRepository,
    ): AddDraftPlant = AddDraftPlantImpl(repository)

    @Provides
    @Singleton
    fun provideLoadDraftPlant(
        repository: PlantRepository,
    ): LoadDraftPlant = LoadDraftPlantImpl(repository)
}
