package com.ujizin.leafy.domain.usecase.ringtone.implementation

import com.ujizin.leafy.domain.model.Ringtone
import com.ujizin.leafy.domain.repository.RingtoneRepository
import com.ujizin.leafy.domain.usecase.ringtone.LoadRingtones
import kotlinx.coroutines.flow.Flow

internal class LoadRingtonesImpl(
    private val ringtoneRepository: RingtoneRepository,
) : LoadRingtones {

    override fun invoke(): Flow<List<Ringtone>> = ringtoneRepository.getRingtones()
}
