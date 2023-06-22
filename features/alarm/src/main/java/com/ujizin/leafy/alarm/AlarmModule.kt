package com.ujizin.leafy.alarm

import android.content.Context
import com.ujizin.leafy.domain.usecase.alarm.LoadAlarm
import com.ujizin.leafy.domain.usecase.plant.LoadPlant
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
    fun provideShowAlarm(
        loadPlant: LoadPlant,
        loadAlarm: LoadAlarm,
        alarmScheduler: AlarmScheduler,
    ) = ShowAlarm(loadPlant, loadAlarm, alarmScheduler)
}
