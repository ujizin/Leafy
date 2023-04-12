package com.ujizin.leafy.core.local.mapper

import com.ujizin.leafy.core.local.model.AlarmEntity
import com.ujizin.leafy.core.repository.model.Alarm

/**
 * Mapper between [Alarm] and [AlarmEntity]
 * */
internal class AlarmMapper {

    fun toAlarmEntity(alarm: Alarm) = with(alarm) {
        AlarmEntity(
            id = id,
            ringtoneUriString = ringtoneUriString,
            repeatIntervalTimeInMillis = repeatIntervalInMillis,
            minutes = minutes,
            hours = hours,
            enabled = enabled,
            plantId = plantId,
            weekDays = weekDays,
        )
    }

    fun toAlarm(alarm: AlarmEntity) = with(alarm) {
        Alarm(
            id = id,
            plantId = plantId,
            ringtoneUriString = ringtoneUriString,
            repeatIntervalInMillis = repeatIntervalTimeInMillis,
            hours = hours,
            enabled = enabled,
            minutes = minutes,
            weekDays = weekDays,
        )
    }
}
