package br.com.devlucasyuji.domain.repository

import br.com.devlucasyuji.domain.model.Alarm
import kotlinx.coroutines.flow.Flow

/**
 * Interface to Alarm Repository implementation.
 * */
interface AlarmRepository {

    /**
     * Insert new alarm on data source.
     *
     * @param alarm alarm to be added
     * */
    fun insertAlarm(alarm: Alarm): Flow<Unit>
}
