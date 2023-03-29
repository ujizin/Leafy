package com.ujizin.leafy.repository

import android.content.Context
import com.ujizin.leafy.domain.repository.AlarmRepository
import com.ujizin.leafy.domain.repository.FileRepository
import com.ujizin.leafy.domain.repository.PlantRepository
import com.ujizin.leafy.domain.repository.RingtoneRepository
import com.ujizin.leafy.domain.repository.UserRepository
import com.ujizin.leafy.repository.datasource.AlarmDataSource
import com.ujizin.leafy.repository.datasource.PlantDataSource
import com.ujizin.leafy.repository.datasource.UserDataSource
import com.ujizin.leafy.repository.implementation.AlarmRepositoryImpl
import com.ujizin.leafy.repository.implementation.FileRepositoryImpl
import com.ujizin.leafy.repository.implementation.PlantRepositoryImpl
import com.ujizin.leafy.repository.implementation.RingtoneRepositoryImpl
import com.ujizin.leafy.repository.implementation.UserRepositoryImpl
import com.ujizin.leafy.repository.mapper.AlarmMapper
import com.ujizin.leafy.repository.mapper.PlantMapper
import com.ujizin.leafy.repository.mapper.UserMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providePlantRepository(dataSource: PlantDataSource): PlantRepository = PlantRepositoryImpl(
        dataSource = dataSource,
        mapper = PlantMapper(),
    )

    @Provides
    @Singleton
    fun provideAlarmRepository(dataSource: AlarmDataSource): AlarmRepository = AlarmRepositoryImpl(
        dataSource = dataSource,
        mapper = AlarmMapper()
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

    @Provides
    @Singleton
    fun provideRingtoneRepository(
        @ApplicationContext context: Context
    ): RingtoneRepository = RingtoneRepositoryImpl(context)
}
