package com.ujizin.leafy.core.repository.mapper

import android.net.Uri
import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.model.WeekDay
import com.ujizin.leafy.core.repository.model.Alarm as RepoAlarm

/**
 * Alarm mapper between domain and data modules.
 * */
class AlarmMapper {

    fun toRepo(alarm: Alarm) = with(alarm) {
        RepoAlarm(
            id = id,
            plantId = plantId,
            ringtoneUriString = ringtoneUri.toString(),
            hours = hours,
            minutes = minutes,
            enabled = enabled,
            weekDays = weekDays.map(WeekDay::name),
        )
    }

    fun toDomain(alarm: RepoAlarm) = with(alarm) {
        Alarm(
            id = id,
            plantId = plantId,
            ringtoneUri = Uri.parse(ringtoneUriString),
            hours = hours,
            minutes = minutes,
            enabled = enabled,
            weekDays = weekDays.map(WeekDay::valueOf),
        )
    }
}
