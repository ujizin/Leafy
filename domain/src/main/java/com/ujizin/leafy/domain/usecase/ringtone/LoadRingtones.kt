package com.ujizin.leafy.domain.usecase.ringtone

import com.ujizin.leafy.domain.model.Ringtone
import kotlinx.coroutines.flow.Flow

/**
 * Get all ringtones from android system source.
 * */
interface LoadRingtones {

    operator fun invoke(): Flow<List<Ringtone>>
}
