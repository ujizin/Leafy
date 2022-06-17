package br.com.devlucasyuji.local.datasource

import br.com.devlucasyuji.local.dao.AlarmDao
import br.com.devlucasyuji.local.mapper.AlarmMapper
import br.com.devlucasyuji.repository.datasource.AlarmDataSource
import br.com.devlucasyuji.repository.model.Alarm

internal class AlarmLocalDataSource(
    private val alarmDao: AlarmDao,
    private val mapper: AlarmMapper,
) : AlarmDataSource {
    override suspend fun insertAlarm(alarm: Alarm) {
        alarmDao.insert(mapper.toAlarmEntity(alarm))
    }

    override suspend fun insertAlarms(alarms: List<Alarm>) {
        val alarmEntities = alarms.map { mapper.toAlarmEntity(it) }
        alarmDao.insert(*alarmEntities.toTypedArray())
    }

    override suspend fun getAlarms(): List<Alarm> = alarmDao.getAll().map(mapper::toAlarm)

    override suspend fun updateAlarm(alarm: Alarm) {
        alarmDao.update(mapper.toAlarmEntity(alarm))
    }

    override suspend fun deleteAlarm(alarm: Alarm) {
        alarmDao.delete(mapper.toAlarmEntity(alarm))
    }
}
