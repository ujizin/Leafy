package br.com.devlucasyuji.repository.mapper

import android.net.Uri
import br.com.devlucasyuji.domain.model.Alarm
import br.com.devlucasyuji.repository.model.Alarm as RepoAlarm

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
