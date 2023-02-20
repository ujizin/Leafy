package br.com.devlucasyuji

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.AlarmManagerCompat
import br.com.devlucasyuji.extensions.alarmManager
import br.com.devlucasyuji.model.RepeatMode
import br.com.devlucasyuji.model.RepeatMode.Companion.NO_REPEAT
import br.com.devlucasyuji.receiver.AlarmReceiver
import java.util.Calendar

class AlarmScheduler(private val context: Context) {

    /**
     * Schedule alarm on Android System.
     *
     * @param type alarm manager's type, default is rtc wakeup
     * @param hours the alarm's hours
     * @param minutes the alarm's minutes
     * @param ringtoneUri the alarm's ringtone uri
     * @param intervalInMillis interval timer to alarm repeats
     * */
    fun scheduleAlarm(
        type: Int = AlarmManager.RTC_WAKEUP,
        hours: Int,
        minutes: Int,
        ringtoneUri: Uri,
        intervalInMillis: Long
    ) {
        when (intervalInMillis) {
            RepeatMode.NO_REPEAT -> setOnceAlarm(type, hours, minutes, ringtoneUri)
            else -> setRepeatAlarm(intervalInMillis, type, hours, minutes, ringtoneUri)
        }
    }

    /**
     * Set alarm in repeat mode.
     *
     * @param intervalInMillis interval timer to alarm repeats
     * @param type alarm manager's type, default is rtc wakeup
     * @param hours the alarm's hours
     * @param minutes the alarm's minutes
     * @param ringtoneUri the alarm's ringtone uri
     * */
    private fun setRepeatAlarm(
        intervalInMillis: Long,
        type: Int,
        hours: Int,
        minutes: Int,
        ringtoneUri: Uri
    ) {
        context.alarmManager.setRepeating(
            type,
            getTimeInMillis(hours, minutes),
            intervalInMillis,
            createPendingIntent(ringtoneUri, intervalInMillis)
        )
    }

    /**
     * Set once and exact alarm.
     *
     * @param type alarm manager's type, default is rtc wakeup
     * @param hours the alarm's hours
     * @param minutes the alarm's minutes
     * @param ringtoneUri the alarm's ringtone uri
     * */
    private fun setOnceAlarm(type: Int, hours: Int, minutes: Int, ringtoneUri: Uri) {
        AlarmManagerCompat.setExactAndAllowWhileIdle(
            context.alarmManager,
            type,
            getTimeInMillis(hours, minutes),
            createPendingIntent(ringtoneUri)
        )
    }

    /**
     * get time from alarm in milliseconds.
     *
     * @param hours the timer's hours
     * @param minutes the timer's minutes
     * */
    private fun getTimeInMillis(
        hours: Int,
        minutes: Int
    ): Long = with(Calendar.getInstance()) {
        val currentTimeInMillis = System.currentTimeMillis()
        timeInMillis = currentTimeInMillis
        set(Calendar.HOUR_OF_DAY, hours)
        set(Calendar.MINUTE, minutes)
        if (currentTimeInMillis <= System.currentTimeMillis()) {
            add(Calendar.DATE, 1)
        }

        return@with timeInMillis
    }

    /**
     * Create pending intent for alarm
     *
     * @param ringtoneUri the alarm's ringtone
     * */
    private fun createPendingIntent(
        ringtoneUri: Uri,
        intervalInMillis: Long = NO_REPEAT
    ): PendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        Intent(context, AlarmReceiver::class.java).apply {
            putExtra(AlarmReceiver.RINGTONE_CONTENT_EXTRA, ringtoneUri.toString())
            putExtra(AlarmReceiver.REPEAT_MODE, intervalInMillis)
        },
        PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
}