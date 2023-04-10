package com.ujizin.leafy.domain.usecase.alarm

import com.ujizin.leafy.domain.repository.AlarmRepository
import com.ujizin.leafy.domain.usecase.alarm.implementation.AddAlarmImpl
import com.ujizin.leafy.domain.usecase.alarm.implementation.LoadAlarmImpl
import com.ujizin.leafy.domain.usecase.alarm.implementation.LoadAlarmsImpl
import com.ujizin.leafy.domain.usecase.alarm.implementation.UpdateAlarmImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AlarmModule {

    @Provides
    @Singleton
    fun provideAddAlarm(
        repository: AlarmRepository,
    ): AddAlarm = AddAlarmImpl(repository)

    @Provides
    @Singleton
    fun provideLoadAlarm(
        repository: AlarmRepository,
    ): LoadAlarm = LoadAlarmImpl(repository)

    @Provides
    @Singleton
    fun provideLoadAlarms(
        repository: AlarmRepository,
    ): LoadAlarms = LoadAlarmsImpl(repository)

    @Provides
    @Singleton
    fun provideUpdateAlarm(
        repository: AlarmRepository,
    ): UpdateAlarm = UpdateAlarmImpl(repository)
}
