package com.ujizin.leafy.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.app.AlarmManagerCompat
import com.ujizin.leafy.alarm.extensions.alarmManager
import com.ujizin.leafy.alarm.model.RepeatMode
import com.ujizin.leafy.alarm.model.RepeatMode.Companion.NO_REPEAT
import com.ujizin.leafy.alarm.receiver.AlarmReceiver
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
     * @param bundle bundle for pending intent
     * */
    fun scheduleAlarm(
        type: Int = AlarmManager.RTC_WAKEUP,
        hours: Int,
        minutes: Int,
        ringtoneUri: Uri,
        intervalInMillis: Long,
        bundle: Bundle = Bundle.EMPTY
    ) {
        when (intervalInMillis) {
            RepeatMode.NO_REPEAT -> setOnceAlarm(type, hours, minutes, ringtoneUri, bundle)
            else -> setRepeatAlarm(intervalInMillis, type, hours, minutes, ringtoneUri, bundle)
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
     * @param bundle bundle for pending intent
     * */
    private fun setRepeatAlarm(
        intervalInMillis: Long,
        type: Int,
        hours: Int,
        minutes: Int,
        ringtoneUri: Uri,
        bundle: Bundle,
    ) {
        context.alarmManager.setRepeating(
            type,
            getTimeInMillis(hours, minutes),
            intervalInMillis,
            createPendingIntent(ringtoneUri, intervalInMillis, bundle)
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
    private fun setOnceAlarm(
        type: Int,
        hours: Int,
        minutes: Int,
        ringtoneUri: Uri,
        bundle: Bundle,
    ) {
        AlarmManagerCompat.setExactAndAllowWhileIdle(
            context.alarmManager,
            type,
            getTimeInMillis(hours, minutes),
            createPendingIntent(ringtoneUri, bundle = bundle)
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
        intervalInMillis: Long = NO_REPEAT,
        bundle: Bundle
    ): PendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        Intent(context, AlarmReceiver::class.java).apply {
            action = AlarmReceiver.SCHEDULE_ALARM_ACTION
            putExtra(AlarmReceiver.RINGTONE_CONTENT_EXTRA, ringtoneUri.toString())
            putExtra(AlarmReceiver.REPEAT_MODE, intervalInMillis)
            putExtras(bundle)
        },
        PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
}