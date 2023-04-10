package com.ujizin.leafy.core.repository.mapper

import android.net.Uri
import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.core.repository.model.Alarm as RepoAlarm

/**
 * Alarm mapper between domain and data modules.
 * */
class AlarmMapper {

    fun toRepo(alarm: Alarm) = with(alarm) {
        RepoAlarm(
            plantId = plantId,
            ringtoneUriString = ringtoneUri.toString(),
            repeatIntervalInMillis = repeatIntervalInMillis,
            hours = hours,
            minutes = minutes,
            enabled = enabled
        )
    }

    fun toDomain(alarm: RepoAlarm) = with(alarm) {
        Alarm(
            plantId = plantId,
            ringtoneUri = Uri.parse(ringtoneUriString),
            repeatIntervalInMillis = repeatIntervalInMillis,
            hours = hours,
            minutes = minutes,
            enabled = enabled,
        )
    }
}
