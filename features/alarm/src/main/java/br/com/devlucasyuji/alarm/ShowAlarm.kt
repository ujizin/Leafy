package br.com.devlucasyuji.alarm

import android.content.Context
import android.content.Intent
import android.net.Uri
import br.com.devlucasyuji.alarm.receiver.AlarmReceiver
import br.com.devlucasyuji.components.R
import br.com.devlucasyuji.domain.result.Result
import br.com.devlucasyuji.domain.usecase.plant.LoadPlant
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

/**
 * Show alarm use case
 * */
class ShowAlarm(
    private val loadPlant: LoadPlant,
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
