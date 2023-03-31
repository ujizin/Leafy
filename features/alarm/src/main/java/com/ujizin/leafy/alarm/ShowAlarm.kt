package com.ujizin.leafy.alarm

import android.content.Context
import android.content.Intent
import android.net.Uri
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
        val plantResult = loadPlant(plantId).first()

        if (plantResult is Result.Success) {
            val plant = plantResult.data
            val uri = Uri.parse(intent.getStringExtra(AlarmReceiver.RINGTONE_CONTENT_EXTRA))
            val title = context.getString(R.string.app_name)
            val description = plant?.title ?: context.getString(R.string.alarm)

            AlarmNotificator.show(context, title, description, uri)
        }

        emit(Unit)
    }
}
