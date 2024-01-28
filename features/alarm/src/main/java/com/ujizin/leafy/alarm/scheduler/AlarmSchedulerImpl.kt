package com.ujizin.leafy.alarm.scheduler

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.app.AlarmManagerCompat
import androidx.core.net.toUri
import com.ujizin.leafy.alarm.extensions.alarmManager
import com.ujizin.leafy.alarm.receiver.AlarmReceiver
import java.util.Calendar

internal class AlarmSchedulerImpl(private val context: Context) : AlarmScheduler {

    override fun scheduleAlarm(
        type: Int,
        hours: Int,
        minutes: Int,
        ringtoneUri: String,
        bundle: Bundle,
    ) {
        setAlarm(type, hours, minutes, ringtoneUri.toUri(), bundle)
    }

    /**
     * Set once and exact alarm.
     *
     * @param type alarm manager's type, default is rtc wakeup
     * @param hours the alarm's hours
     * @param minutes the alarm's minutes
     * @param ringtoneUri the alarm's ringtone uri
     * */
    private fun setAlarm(
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
            createPendingIntent(ringtoneUri, bundle = bundle),
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
        minutes: Int,
    ): Long = with(Calendar.getInstance()) {
        val currentTimeInMillis = System.currentTimeMillis()
        timeInMillis = currentTimeInMillis
        set(Calendar.HOUR_OF_DAY, hours)
        set(Calendar.MINUTE, minutes)
        if (timeInMillis <= currentTimeInMillis) {
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
        bundle: Bundle,
    ): PendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        Intent(context, AlarmReceiver::class.java).apply {
            action = AlarmReceiver.SCHEDULE_ALARM_ACTION
            putExtra(AlarmReceiver.RINGTONE_CONTENT_EXTRA, ringtoneUri.toString())
            putExtras(bundle)
        },
        PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE,
    )
}
