package br.com.devlucasyuji.local.mapper

import br.com.devlucasyuji.local.model.AlarmEntity
import br.com.devlucasyuji.repository.model.Alarm

internal class AlarmMapper {

    fun toAlarmEntity(alarm: Alarm) = with(alarm) {
        AlarmEntity(id, ring, photoId)
    }

    fun toAlarm(alarm: AlarmEntity) = with(alarm) {
        Alarm(id, ring, photoId)
    }
}
