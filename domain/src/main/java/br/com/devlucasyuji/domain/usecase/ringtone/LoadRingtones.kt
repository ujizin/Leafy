package br.com.devlucasyuji.domain.usecase.ringtone

import br.com.devlucasyuji.domain.model.Ringtone
import kotlinx.coroutines.flow.Flow

/**
 * Get all ringtones from android system source.
 * */
interface LoadRingtones {

    operator fun invoke(): Flow<List<Ringtone>>
}
