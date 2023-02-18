package br.com.devlucasyuji.domain.repository

import br.com.devlucasyuji.domain.model.Ringtone
import kotlinx.coroutines.flow.Flow

/**
 * Ringtone repository from android system.
 * */
interface RingtoneRepository {

    /**
     * Get list of ringtone.
     * */
    fun getRingtones(): Flow<List<Ringtone>>
}
