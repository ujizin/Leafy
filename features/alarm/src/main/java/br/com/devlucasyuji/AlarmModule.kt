package br.com.devlucasyuji

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AlarmModule {

    @Provides
    fun provideAlarmScheduler(@ApplicationContext context: Context) = AlarmScheduler(context)
}