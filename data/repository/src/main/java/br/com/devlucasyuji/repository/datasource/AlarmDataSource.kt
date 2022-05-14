package br.com.devlucasyuji.repository.datasource

import br.com.devlucasyuji.repository.model.Alarm

interface AlarmDataSource {
    /**
     * Insert a new album.
     *
     * @param alarm alarm to be added
     * */
    suspend fun insertAlarm(alarm: Alarm)

    /**
     * Insert a new alarm list.
     *
     * @param alarms alarms' list to be added
     * */
    suspend fun insertAlarms(alarms: List<Alarm>)

    /**
     * Get all alarms.
     *
     * @return all alarms added
     * */
    suspend fun getAlarms(): List<Alarm>

    /**
     * Find alarm by id.
     *
     * @param alarmId id's alarm
     * @return alarm found or null
     * */
    suspend fun findAlarm(alarmId: String): Alarm?

    /**
     * Update alarm passed on parameter
     *
     * @param alarm alarm to be updated
     * */
    suspend fun updateAlarm(alarm: Alarm)

    /**
     * Delete alarm passed on parameter
     *
     * @param alarm alarm to be deleted
     * */
    suspend fun deleteAlarm(alarm: Alarm)
}