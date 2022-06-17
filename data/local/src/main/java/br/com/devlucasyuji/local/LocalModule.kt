package br.com.devlucasyuji.local

import android.content.Context
import androidx.room.Room
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
}
