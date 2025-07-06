package com.ujizin.leafy.alarm.usecase

import android.content.Context
import android.content.Intent
import com.ujizin.leafy.alarm.AlarmService
import com.ujizin.leafy.alarm.extensions.getAlarmIntent
import com.ujizin.leafy.alarm.receiver.AlarmReceiver.Companion.ALARM_ID_EXTRA
import com.ujizin.leafy.alarm.receiver.AlarmReceiver.Companion.RINGTONE_CONTENT_EXTRA
import com.ujizin.leafy.core.components.R
import com.ujizin.leafy.core.ui.extensions.currentDay
import com.ujizin.leafy.core.ui.extensions.plus
import com.ujizin.leafy.domain.model.Plant
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach

class StartAlarmUseCase(
    private val context: Context,
    private val schedulePlantAlarmUseCase: SchedulePlantAlarmUseCase,
    private val dispatcher: CoroutineDispatcher,
) {
    private val Intent.alarmId: Long
        get() = getLongExtra(ALARM_ID_EXTRA, -1)

    private val Intent.ringtoneStringify: String?
        get() = getStringExtra(RINGTONE_CONTENT_EXTRA)

    operator fun invoke(intent: Intent) = schedulePlantAlarmUseCase(
        alarmId = intent.alarmId,
        actualDay = currentDay + 1,
    ).onEach { plant ->
        context.ringPlantAlarm(intent, plant)
    }.flowOn(dispatcher)

    private fun Context.ringPlantAlarm(intent: Intent, plant: Plant) = startAlarmService(
        serviceIntent = getAlarmServiceIntent(plant, intent.ringtoneStringify),
    )

    private fun Context.getAlarmServiceIntent(
        plant: Plant,
        ringtone: String?,
    ) = getAlarmIntent().apply {
        putExtra(AlarmService.PLANT_ID, plant.id)
        putExtra(AlarmService.TITLE_ARG, getString(R.string.app_name))
        putExtra(AlarmService.DESCRIPTION_ARG, plant.title)
        putExtra(AlarmService.RINGTONE_URI_STRINGIFY_ARG, ringtone)
    }

    private fun Context.startAlarmService(serviceIntent: Intent) {
        startService(serviceIntent)
    }
}
