package com.ujizin.leafy.alarm.receiver

import android.app.AlarmManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.PowerManager
import com.ujizin.leafy.alarm.AlarmService
import com.ujizin.leafy.alarm.extensions.alarmId
import com.ujizin.leafy.alarm.extensions.ringtoneStringify
import com.ujizin.leafy.alarm.scheduler.AlarmScheduler
import com.ujizin.leafy.alarm.usecase.SchedulePlantAlarm
import com.ujizin.leafy.core.components.R
import com.ujizin.leafy.core.ui.extensions.createWakeLock
import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.result.mapResult
import com.ujizin.leafy.domain.usecase.alarm.LoadAlarms
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.time.Duration.Companion.minutes

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    private val tag by lazy {
        "tag-${javaClass.canonicalName ?: javaClass.name}"
    }

    private lateinit var wakeLock: PowerManager.WakeLock

    @Inject
    lateinit var schedulePlantAlarm: SchedulePlantAlarm

    @Inject
    lateinit var loadAlarms: LoadAlarms

    @Inject
    lateinit var alarmScheduler: AlarmScheduler

    @OptIn(DelicateCoroutinesApi::class)
    override fun onReceive(context: Context, intent: Intent) {
        wakeLock = context.createWakeLock(tag).apply {
            acquire(10.minutes.inWholeMicroseconds)
        }

        when (intent.action) {
            SCHEDULE_ALARM_ACTION -> schedulePlantAlarm(alarmId = intent.alarmId).onEach { plant ->
                context.ringPlantAlarm(intent, plant)
            }

            Intent.ACTION_BOOT_COMPLETED,
            Intent.ACTION_MY_PACKAGE_REPLACED,
            AlarmManager.ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED -> loadAlarms()
                .mapResult()
                .onEach { alarms -> alarms.forEach(alarmScheduler::scheduleAlarm) }

            else -> emptyFlow()
        }.onCompletion {
            wakeLock.release()
        }.launchIn(GlobalScope)
    }

    private fun Context.ringPlantAlarm(intent: Intent, plant: Plant) = startAlarmService(
        serviceIntent = getAlarmServiceIntent(plant, intent.ringtoneStringify)
    )

    private fun Context.getAlarmServiceIntent(
        plant: Plant,
        ringtone: String?,
    ) = Intent(this, AlarmService::class.java).apply {
        putExtra(AlarmService.PLANT_ID, plant.id)
        putExtra(AlarmService.TITLE_ARG, getString(R.string.app_name))
        putExtra(AlarmService.DESCRIPTION_ARG, plant.title)
        putExtra(AlarmService.RINGTONE_URI_STRINGIFY_ARG, ringtone)
    }

    private fun Context.startAlarmService(serviceIntent: Intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }
    }

    internal companion object {
        const val RINGTONE_CONTENT_EXTRA = "ringtone_content"

        const val ALARM_ID_EXTRA = "alarm_id"

        const val SCHEDULE_ALARM_ACTION = "com.ujizin.leafy.alarm.SCHEDULE_ALARM"
    }
}
