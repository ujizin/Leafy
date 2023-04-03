package com.ujizin.leafy.alarm

import android.content.Context
import android.content.Intent
import android.net.Uri
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
        Log.d("Alarm Plant id", plantId.toString())

        val plantResult = loadPlant(plantId).first { it is Result.Success }

        if (plantResult is Result.Success) {
            val plant = plantResult.data
            val ringtone = intent.getStringExtra(AlarmReceiver.RINGTONE_CONTENT_EXTRA)
            Log.d("alarm ringtone", "$ringtone")
            val uri = Uri.parse(ringtone)
            val title = context.getString(R.string.app_name)
            val description = plant?.title ?: context.getString(R.string.alarm)

            Log.d("Notification", "$title, $description")
            AlarmNotificator.show(context, title, description, uri)
        }

        emit(Unit)
    }
}
