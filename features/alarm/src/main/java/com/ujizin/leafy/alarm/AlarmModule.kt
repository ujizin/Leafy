package com.ujizin.leafy.alarm

import android.content.Context
import android.media.MediaPlayer
import com.ujizin.leafy.alarm.notificator.AlarmNotificator
import com.ujizin.leafy.alarm.notificator.AlarmNotificatorImpl
import com.ujizin.leafy.alarm.player.AlarmPlayer
import com.ujizin.leafy.alarm.scheduler.AlarmScheduler
import com.ujizin.leafy.alarm.scheduler.AlarmSchedulerImpl
import com.ujizin.leafy.alarm.usecase.RescheduleAllAlarmsUseCase
import com.ujizin.leafy.alarm.usecase.SchedulePlantAlarmUseCase
import com.ujizin.leafy.domain.dispatcher.IoDispatcher
import com.ujizin.leafy.domain.usecase.alarm.load.LoadAlarmUseCase
import com.ujizin.leafy.domain.usecase.alarm.load.LoadAlarmsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AlarmModule {

    @Provides
    @Singleton
    fun provideAlarmScheduler(
        @ApplicationContext context: Context,
    ): AlarmScheduler = AlarmSchedulerImpl(context)

    @Provides
    fun provideShowAlarmUseCase(
        loadAlarm: LoadAlarmUseCase,
        alarmScheduler: AlarmScheduler,
    ) = SchedulePlantAlarmUseCase(
        loadAlarmUseCase = loadAlarm,
        alarmScheduler = alarmScheduler,
    )

    @Provides
    fun provideRescheduleAllAlarmUseCase(
        loadAlarmUseCase: LoadAlarmsUseCase,
        alarmScheduler: AlarmScheduler,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ) = RescheduleAllAlarmsUseCase(
        loadAlarmsUseCase = loadAlarmUseCase,
        alarmScheduler = alarmScheduler,
        dispatcher = ioDispatcher,
    )

    @Provides
    @Singleton
    fun provideAlarmNotificator(
        @ApplicationContext context: Context,
    ): AlarmNotificator = AlarmNotificatorImpl(context)

    @Provides
    fun provideAlarmPlayer(
        @ApplicationContext context: Context,
    ) = AlarmPlayer(
        context = context,
        mediaPlayer = MediaPlayer(),
    )
}
