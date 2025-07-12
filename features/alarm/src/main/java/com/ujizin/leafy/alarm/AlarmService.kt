package com.ujizin.leafy.alarm

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.ujizin.leafy.alarm.extensions.alarmId
import com.ujizin.leafy.alarm.extensions.getAlarmIntent
import com.ujizin.leafy.alarm.extensions.plantId
import com.ujizin.leafy.alarm.extensions.ringtoneUri
import com.ujizin.leafy.alarm.notificator.AlarmNotificator
import com.ujizin.leafy.alarm.player.AlarmPlayer
import com.ujizin.leafy.alarm.receiver.StopPlantServiceActivity
import com.ujizin.leafy.alarm.usecase.SchedulePlantAlarmUseCase
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.ui.extensions.currentDay
import com.ujizin.leafy.core.ui.extensions.plus
import com.ujizin.leafy.core.ui.props.RequestCode
import com.ujizin.leafy.domain.dispatcher.IoDispatcher
import com.ujizin.leafy.domain.result.filterNotLoading
import com.ujizin.leafy.domain.result.getOrNull
import com.ujizin.leafy.domain.usecase.plant.load.LoadPlantByAlarmIdUseCase
import com.ujizin.leafy.features.alarm.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.ujizin.leafy.core.components.R as CR

@AndroidEntryPoint
class AlarmService : Service() {

    @Inject
    lateinit var alarmPlayer: AlarmPlayer

    @Inject
    @IoDispatcher
    lateinit var ioDispatcher: CoroutineDispatcher

    @Inject
    lateinit var alarmNotificator: AlarmNotificator

    @Inject
    lateinit var loadPlantUseCaseByAlarmId: LoadPlantByAlarmIdUseCase

    @Inject
    lateinit var schedulePlantAlarmUseCase: SchedulePlantAlarmUseCase

    private val ioScope by lazy { CoroutineScope(ioDispatcher) }

    private val stopAlarmPendingIntent: PendingIntent
        get() = PendingIntent.getService(
            this,
            RequestCode.ALARM,
            applicationContext.getAlarmIntent(STOP_ACTION),
            PendingIntent.FLAG_IMMUTABLE,
        )

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent == null) {
            return super.onStartCommand(intent, flags, startId)
        }

        when (intent.action) {
            STOP_ACTION -> stopService()
            else -> startAlarm(intent)
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun startAlarm(intent: Intent) = combine(
        schedulePlantAlarmUseCase(
            alarmId = intent.alarmId,
            actualDay = currentDay + 1,
        ),
        loadPlantUseCaseByAlarmId(intent.alarmId).filterNotLoading(),
    ) { _, plantResult ->
        val plant = plantResult.getOrNull()
        withContext(Dispatchers.Main) {
            startAlarmNotification(intent, plant?.title, plant?.description)
        }
    }.launchIn(ioScope)

    private fun stopService() {
        stopForeground()
        stopSelf()
    }

    @Suppress("DEPRECATION")
    private fun stopForeground() = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> stopForeground(STOP_FOREGROUND_REMOVE)
        else -> stopForeground(true)
    }

    private fun startAlarmNotification(intent: Intent, title: String?, description: String?) {
        alarmPlayer.play(intent.ringtoneUri)
        startForeground(NOTIFICATION_ID, intent.getNotification(title, description))
    }

    private fun Intent.getNotification(
        title: String?,
        description: String?,
    ): Notification {
        val title = title ?: getString(CR.string.app_name)
        val description = description ?: getString(CR.string.alarm)
        val contentIntent = getPlantPendingIntent(plantId)

        return alarmNotificator.getNotification(
            title = title,
            description = description,
            contentIntent = contentIntent,
            notificationActions = listOf(
                NotificationCompat.Action(0, getString(R.string.stop), stopAlarmPendingIntent),
            ),
        )
    }

    private fun getPlantPendingIntent(
        plantId: Long,
    ) = TaskStackBuilder.create(this).run {
        val deeplinkIntent = Intent(
            Intent.ACTION_VIEW,
            Destination.PlantDetails.createDeeplink(plantId),
        ).apply {
            putExtra(ALARM_SERVICE_EXTRA, true)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val stopService = StopPlantServiceActivity.getIntent(this@AlarmService)
        addNextIntentWithParentStack(deeplinkIntent)
        addNextIntent(stopService)
        getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
    }

    override fun onDestroy() {
        alarmNotificator.cancelNotification(NOTIFICATION_ID)
        alarmPlayer.release()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        const val SCHEDULE_ALARM_ACTION = "com.ujizin.leafy.alarm.SCHEDULE_ALARM"
        private const val NOTIFICATION_ID = 1
        const val STOP_ACTION = "alarm_service_stop"
        internal const val RINGTONE_URI_STRINGIFY_EXTRA = "alarm_ringtone_uri_stringify"
        internal const val ALARM_ID_EXTRA = "alarm_id"
        internal const val PLANT_ID_EXTRA = "alarm_plant_id"
        private const val ALARM_SERVICE_EXTRA = "alarm_service"
    }
}
