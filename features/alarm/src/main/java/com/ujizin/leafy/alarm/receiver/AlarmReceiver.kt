package com.ujizin.leafy.alarm.receiver

import android.app.AlarmManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ujizin.leafy.alarm.AlarmScheduler
import com.ujizin.leafy.alarm.ShowAlarm
import com.ujizin.leafy.alarm.scheduleAlarm
import com.ujizin.leafy.domain.result.mapResult
import com.ujizin.leafy.domain.usecase.alarm.LoadAlarms
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var showAlarm: ShowAlarm

    @Inject
    lateinit var loadAlarms: LoadAlarms

    @Inject
    lateinit var alarmScheduler: AlarmScheduler

    @OptIn(DelicateCoroutinesApi::class)
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            SCHEDULE_ALARM_ACTION -> showAlarm(context, intent).launchIn(GlobalScope)
            Intent.ACTION_BOOT_COMPLETED,
            Intent.ACTION_MY_PACKAGE_REPLACED,
            AlarmManager.ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED -> loadAlarms()
                .mapResult()
                .onEach { alarms -> alarms.forEach(alarmScheduler::scheduleAlarm) }
                .launchIn(GlobalScope)
        }
    }

    internal companion object {
        const val RINGTONE_CONTENT_EXTRA = "ringtone_content"

        const val ALARM_ID_EXTRA = "alarm_id"

        const val SCHEDULE_ALARM_ACTION = "com.ujizin.leafy.alarm.SCHEDULE_ALARM"
    }
}
