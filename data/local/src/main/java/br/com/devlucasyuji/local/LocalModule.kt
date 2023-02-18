package br.com.devlucasyuji.local

import android.content.Context
import androidx.room.Room
import br.com.devlucasyuji.local.datasource.PlantLocalDataSource
import br.com.devlucasyuji.local.datasource.UserLocalDataSource
import br.com.devlucasyuji.local.datastore.UserDataStore
import br.com.devlucasyuji.local.mapper.PlantMapper
import br.com.devlucasyuji.local.mapper.UserMapper
import br.com.devlucasyuji.repository.datasource.PlantDataSource
import br.com.devlucasyuji.repository.datasource.UserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dependency injection of Local Module
 * */
@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database =
        Room.databaseBuilder(
            context,
            Database::class.java,
            Database.NAME
        ).build()

    @Singleton
    @Provides
    fun provideMemoryDatabase(@ApplicationContext context: Context): MemoryDatabase =
        Room.inMemoryDatabaseBuilder(
            context,
            MemoryDatabase::class.java,
        ).build()

    @Singleton
    @Provides
    fun provideLocalPlantDataSource(
        database: Database,
        memoryDatabase: MemoryDatabase,
    ): PlantDataSource = PlantLocalDataSource(
        plantDao = database.plantDao(),
        memoryPlantDao = memoryDatabase.plantDao(),
        mapper = PlantMapper()
    )

    @Singleton
    @Provides
    fun provideUserDataSource(userDataStore: UserDataStore): UserDataSource = UserLocalDataSource(
        userDataStore = userDataStore,
        userMapper = UserMapper()
    )
}
