package com.ujizin.leafy.alarm.receiver

import android.app.AlarmManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ujizin.leafy.alarm.ShowAlarm
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var showAlarm: ShowAlarm

    @OptIn(DelicateCoroutinesApi::class)
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            SCHEDULE_ALARM_ACTION -> showAlarm(context, intent).launchIn(GlobalScope)
            AlarmManager.ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED -> {
                // reschedule the alarms
            }
        }
    }

    internal companion object {
        const val RINGTONE_CONTENT_EXTRA = "ringtone_content"
        const val REPEAT_MODE = "repeat_mode"

        const val ALARM_PLANT_ID_EXTRA = "alarm_plant_id"

        const val SCHEDULE_ALARM_ACTION = "com.ujizin.leafy.alarm.SCHEDULE_ALARM"
    }
}
