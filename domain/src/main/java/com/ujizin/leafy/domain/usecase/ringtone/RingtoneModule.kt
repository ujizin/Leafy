package com.ujizin.leafy.domain.usecase.ringtone

import com.ujizin.leafy.domain.repository.RingtoneRepository
import com.ujizin.leafy.domain.usecase.ringtone.implementation.LoadRingtonesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RingtoneModule {

    @Provides
    @Singleton
    fun provideLoadRingtones(
        repository: RingtoneRepository
    ): LoadRingtones = LoadRingtonesImpl(repository)

}