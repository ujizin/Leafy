package br.com.devlucasyuji.domain.usecase.ringtone.implementation

import br.com.devlucasyuji.domain.model.Ringtone
import br.com.devlucasyuji.domain.repository.RingtoneRepository
import br.com.devlucasyuji.domain.usecase.ringtone.LoadRingtones
import kotlinx.coroutines.flow.Flow

class LoadRingtonesImpl(
    private val ringtoneRepository: RingtoneRepository
) : LoadRingtones {

    override fun invoke(): Flow<List<Ringtone>> = ringtoneRepository.getRingtones()
}