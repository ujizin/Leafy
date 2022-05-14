package br.com.devlucasyuji.local.datasource

import br.com.devlucasyuji.repository.datasource.AlarmDataSource
import br.com.devlucasyuji.repository.model.Alarm

internal class AlarmLocalDataSource : AlarmDataSource {
    override suspend fun insertAlarm(alarm: Alarm) {
        TODO("Not yet implemented")
    }

    override suspend fun insertAlarms(alarms: List<Alarm>) {
        TODO("Not yet implemented")
    }

    override suspend fun getAlarms(): List<Alarm> {
        TODO("Not yet implemented")
    }

    override suspend fun findAlarm(alarmId: String): Alarm? {
        TODO("Not yet implemented")
    }

    override suspend fun updateAlarm(alarm: Alarm) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAlarm(alarm: Alarm) {
        TODO("Not yet implemented")
    }
}