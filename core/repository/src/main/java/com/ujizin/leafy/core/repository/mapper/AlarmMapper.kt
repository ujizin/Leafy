package com.ujizin.leafy.core.repository.mapper

import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.model.WeekDay
import com.ujizin.leafy.core.repository.model.Alarm as RepoAlarm

/**
 * Alarm mapper between domain and data modules.
 * */
internal class AlarmMapper {

    fun toRepo(alarm: Alarm) = with(alarm) {
        RepoAlarm(
            id = id,
            plantId = plantId,
            ringtoneUriString = ringtoneUriContent,
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
            ringtoneUriContent = ringtoneUriString,
            hours = hours,
            minutes = minutes,
            enabled = enabled,
            weekDays = weekDays.map(WeekDay::valueOf),
        )
    }
}
