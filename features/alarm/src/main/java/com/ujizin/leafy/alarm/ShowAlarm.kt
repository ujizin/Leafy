package com.ujizin.leafy.alarm

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.ujizin.leafy.alarm.receiver.AlarmReceiver
import com.ujizin.leafy.core.components.R
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.usecase.plant.LoadPlant
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

/**
 * Show alarm use case
 * */
class ShowAlarm(
    private val loadPlant: LoadPlant
) {

    operator fun invoke(context: Context, intent: Intent) = flow {
        val plantId = intent.getLongExtra(AlarmReceiver.ALARM_PLANT_ID_EXTRA, -1)
        val plantResult = loadPlant(plantId).first { it is Result.Success }

        Log.d("ShowAlarm", "Ok...")
        if (plantResult is Result.Success) {
            val plant = plantResult.data
            val ringtone = intent.getStringExtra(AlarmReceiver.RINGTONE_CONTENT_EXTRA)
            val title = context.getString(R.string.app_name)
            val description = plant?.title ?: context.getString(R.string.alarm)

            val serviceIntent = Intent(context, AlarmService::class.java).apply {
                putExtra(AlarmService.TITLE_ARG, title)
                putExtra(AlarmService.DESCRIPTION_ARG, description)
                putExtra(AlarmService.RINGTONE_URI_STRINGIFY_ARG, ringtone)
            }

            Log.d("ShowAlarm", "Calling service...")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent)
            } else {
                context.startService(serviceIntent)
            }
        }

        emit(Unit)
    }
}
