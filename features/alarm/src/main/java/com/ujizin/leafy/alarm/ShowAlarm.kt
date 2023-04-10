package com.ujizin.leafy.alarm

import android.content.Context
import android.content.Intent
import android.os.Build
import com.ujizin.leafy.alarm.receiver.AlarmReceiver
import com.ujizin.leafy.core.components.R
import com.ujizin.leafy.domain.result.mapResult
import com.ujizin.leafy.domain.usecase.alarm.LoadAlarm
import com.ujizin.leafy.domain.usecase.plant.LoadPlant
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map

/**
 * Show alarm use case
 * */
class ShowAlarm(
    private val loadPlant: LoadPlant,
    private val loadAlarm: LoadAlarm,
) {

    @OptIn(FlowPreview::class)
    operator fun invoke(
        context: Context,
        intent: Intent
    ) = loadAlarm(intent.getLongExtra(AlarmReceiver.ALARM_ID_EXTRA, -1))
        .mapResult()
        .flatMapConcat { alarm ->
            if (!alarm.enabled) return@flatMapConcat emptyFlow()

            loadPlant(alarm.plantId).mapResult().map { plant ->
                val ringtone = intent.getStringExtra(AlarmReceiver.RINGTONE_CONTENT_EXTRA)
                val serviceIntent = Intent(context, AlarmService::class.java).apply {
                    putExtra(AlarmService.TITLE_ARG, context.getString(R.string.app_name))
                    putExtra(AlarmService.DESCRIPTION_ARG, plant.title)
                    putExtra(AlarmService.RINGTONE_URI_STRINGIFY_ARG, ringtone)
                }

                startAlarmService(context, serviceIntent)
            }
        }

    private fun startAlarmService(context: Context, serviceIntent: Intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }
    }
}