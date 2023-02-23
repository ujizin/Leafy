package br.com.devlucasyuji.alarm

import android.content.Context
import br.com.devlucasyuji.domain.usecase.plant.LoadPlant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AlarmModule {

    @Provides
    @Singleton
    fun provideAlarmScheduler(@ApplicationContext context: Context) = AlarmScheduler(context)

    @Provides
    @Singleton
    fun provideShowAlarm(loadPlant: LoadPlant) = ShowAlarm(loadPlant)
}