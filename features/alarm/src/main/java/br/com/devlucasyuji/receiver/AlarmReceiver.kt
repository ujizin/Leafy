package br.com.devlucasyuji.receiver

import android.app.AlarmManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            AlarmManager.ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED -> {
                // reschedule the alarms
            }
        }
    }

    companion object {
        internal const val RINGTONE_CONTENT_EXTRA = "ringtone_content"
        internal const val REPEAT_MODE = "repeat_mode"
    }
}