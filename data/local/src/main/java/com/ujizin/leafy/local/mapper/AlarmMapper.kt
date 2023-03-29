package com.ujizin.leafy.local.mapper

import com.ujizin.leafy.local.model.AlarmEntity
import com.ujizin.leafy.repository.model.Alarm

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
