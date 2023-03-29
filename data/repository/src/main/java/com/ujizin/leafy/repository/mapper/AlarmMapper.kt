package com.ujizin.leafy.repository.mapper

import android.net.Uri
import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.repository.model.Alarm as RepoAlarm

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
            minutes = minutes
        )
    }

    fun toDomain(alarm: RepoAlarm) = with(alarm) {
        val ringtoneUri = Uri.parse(ringtoneUriString)
        Alarm(
            plantId = plantId,
            ringtoneUri = ringtoneUri,
            repeatIntervalInMillis = repeatIntervalInMillis,
            hours = hours,
            minutes = minutes
        )
    }
}
