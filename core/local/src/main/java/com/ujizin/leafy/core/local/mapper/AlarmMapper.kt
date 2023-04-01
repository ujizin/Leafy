package com.ujizin.leafy.core.local.mapper

import com.ujizin.leafy.core.local.model.AlarmEntity
import com.ujizin.leafy.core.repository.model.Alarm

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
