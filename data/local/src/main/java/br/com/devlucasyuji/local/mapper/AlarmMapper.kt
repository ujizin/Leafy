package br.com.devlucasyuji.local.mapper

import br.com.devlucasyuji.local.model.AlarmEntity
import br.com.devlucasyuji.repository.model.Alarm

/**
 * Mapper between [Alarm] and [AlarmEntity]
 * */
internal class AlarmMapper {

    fun toAlarmEntity(alarm: Alarm) = with(alarm) {
        AlarmEntity(id, ringtoneUriString, plantId, repeatIntervalInMillis, hours, minutes)
    }

    fun toAlarm(alarm: AlarmEntity) = with(alarm) {
        Alarm(id, plantId, ringtoneUriString, repeatIntervalTimeInMillis, hours, minutes)
    }
}
