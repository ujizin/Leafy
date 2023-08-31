package com.ujizin.leafy.alarm

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.ujizin.leafy.core.ui.props.RequestCode
import com.ujizin.leafy.features.alarm.R
import java.io.IOException
import com.ujizin.leafy.core.components.R as CR

class AlarmService : Service() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            STOP_ACTION -> stopService()
            else -> startAlarm(intent)
        }

        return START_NOT_STICKY
    }

    private fun stopService() {
        stopForeground()
        stopSelf()
    }

    private fun stopForeground() = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> stopForeground(STOP_FOREGROUND_DETACH)
        else -> stopForeground(true)
    }

    private fun startAlarm(intent: Intent?) {
        playRingtone(getRingtoneUri(intent))
        val notification = getNotification(intent)
        startForeground(1, notification)
    }

    private fun getRingtoneUri(
        intent: Intent?,
    ): Uri = intent?.getStringExtra(RINGTONE_URI_STRINGIFY_ARG)
        ?.let(Uri::parse)
        .orDefaultRingtone()

    private fun getNotification(intent: Intent?): Notification {
        val title = intent?.getStringExtra(TITLE_ARG) ?: getString(CR.string.app_name)
        val description = intent?.getStringExtra(DESCRIPTION_ARG) ?: getString(CR.string.alarm)
        val contentIntent = launcherAppIntent()
        val stopIntent = alarmIntent(action = STOP_ACTION)

        return AlarmNotificator.getNotification(
            context = this,
            title = title,
            description = description,
            contentIntent = contentIntent,
            notificationActions = listOf(
                NotificationCompat.Action(0, getString(R.string.stop), stopIntent),
            ),
        )
    }

    private fun alarmIntent(action: String) = PendingIntent.getService(
        this,
        RequestCode.ALARM,
        Intent(this, AlarmService::class.java).apply {
            this.action = action
        },
        PendingIntent.FLAG_IMMUTABLE,
    )

    private fun launcherAppIntent() = PendingIntent.getActivity(
        this,
        RequestCode.ALARM,
        packageManager.getLaunchIntentForPackage(packageName)?.apply {
            action = STOP_ACTION
        },
        PendingIntent.FLAG_IMMUTABLE,
    )

    private fun playRingtone(uri: Uri) {
        try {
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ALARM)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()

            mediaPlayer = MediaPlayer().apply {
                setDataSource(this@AlarmService, uri)
                setAudioAttributes(audioAttributes)
                isLooping = true
                prepareAsync()
                setOnPreparedListener { start() }
            }
        } catch (_: IOException) {
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        internal const val TITLE_ARG = "alarm_title"
        internal const val DESCRIPTION_ARG = "alarm_description"
        internal const val RINGTONE_URI_STRINGIFY_ARG = "alarm_ringtone_uri_stringify"
        const val STOP_ACTION = "alarm_service_stop"
    }
}

private fun Uri?.orDefaultRingtone(): Uri =
    this ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
