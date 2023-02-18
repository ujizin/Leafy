package br.com.devlucasyuji.repository

import android.content.Context
import android.media.RingtoneManager
import br.com.devlucasyuji.domain.repository.FileRepository
import br.com.devlucasyuji.domain.repository.PlantRepository
import br.com.devlucasyuji.domain.repository.RingtoneRepository
import br.com.devlucasyuji.domain.repository.UserRepository
import br.com.devlucasyuji.repository.datasource.PlantDataSource
import br.com.devlucasyuji.repository.datasource.UserDataSource
import br.com.devlucasyuji.repository.implementation.FileRepositoryImpl
import br.com.devlucasyuji.repository.implementation.PlantRepositoryImpl
import br.com.devlucasyuji.repository.implementation.RingtoneRepositoryImpl
import br.com.devlucasyuji.repository.implementation.UserRepositoryImpl
import br.com.devlucasyuji.repository.mapper.PlantMapper
import br.com.devlucasyuji.repository.mapper.UserMapper
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
    ): RingtoneRepository = RingtoneRepositoryImpl(RingtoneManager(context))
}
