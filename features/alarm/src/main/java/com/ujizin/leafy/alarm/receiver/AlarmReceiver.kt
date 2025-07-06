package com.ujizin.leafy.alarm.receiver

import android.app.AlarmManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ujizin.leafy.alarm.scheduler.AlarmScheduler
import com.ujizin.leafy.alarm.usecase.RescheduleAllAlarmsUseCase
import com.ujizin.leafy.alarm.usecase.StartAlarmUseCase
import com.ujizin.leafy.domain.dispatcher.IoDispatcher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @IoDispatcher
    @Inject
    lateinit var ioDispatcher: CoroutineDispatcher

    @Inject
    lateinit var startAlarmUseCase: StartAlarmUseCase

    @Inject
    lateinit var rescheduleAllAlarmsUseCase: RescheduleAllAlarmsUseCase

    @Inject
    lateinit var alarmScheduler: AlarmScheduler

    private val coroutineScope = CoroutineScope(ioDispatcher)

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            SCHEDULE_ALARM_ACTION -> startAlarmUseCase(intent)
            AlarmManager.ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED,
            Intent.ACTION_TIME_CHANGED,
            Intent.ACTION_BOOT_COMPLETED,
            -> rescheduleAllAlarmsUseCase()

            else -> emptyFlow()
        }.launchIn(coroutineScope)
    }

    internal companion object {

        const val RINGTONE_CONTENT_EXTRA = "ringtone_content"

        const val ALARM_ID_EXTRA = "alarm_id"

        const val SCHEDULE_ALARM_ACTION = "com.ujizin.leafy.alarm.SCHEDULE_ALARM"
    }
}
