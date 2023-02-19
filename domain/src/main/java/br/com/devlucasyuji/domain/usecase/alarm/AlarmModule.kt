package br.com.devlucasyuji.domain.usecase.alarm

import br.com.devlucasyuji.domain.repository.AlarmRepository
import br.com.devlucasyuji.domain.usecase.alarm.implementation.AddAlarmImpl
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
        repository: AlarmRepository
    ): AddAlarm = AddAlarmImpl(repository)
}
