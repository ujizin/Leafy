package com.ujizin.leafy.alarm

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.IBinder
import com.ujizin.leafy.core.components.R
import java.io.IOException


class AlarmService : Service() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            STOP_ACTION -> startActivity(packageManager.getLaunchIntentForPackage(packageName)).run {
                stopSelf()
            }
            else -> startAlarm(intent)
        }
        return START_NOT_STICKY
    }

    private fun startAlarm(intent: Intent?) {
        val title = intent?.getStringExtra(TITLE_ARG) ?: getString(R.string.app_name)
        val description = intent?.getStringExtra(DESCRIPTION_ARG) ?: getString(R.string.alarm)
        val uri = intent?.getStringExtra(RINGTONE_URI_STRINGIFY_ARG)
            ?.let(Uri::parse)
            .orDefaultRingtone()

        val serviceIntent = Intent(this, AlarmService::class.java).apply {
            action = STOP_ACTION
        }

        val pendingIntent = PendingIntent.getService(
            this,
            0,
            serviceIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = AlarmNotificator.getNotification(
            context = this,
            title = title,
            description = description,
            contentIntent = pendingIntent,
        )

        playRingtone(uri)
        startForeground(1, notification)
    }

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
        private const val STOP_ACTION = "alarm_service_stop"
        internal const val TITLE_ARG = "alarm_title"
        internal const val DESCRIPTION_ARG = "alarm_description"
        internal const val RINGTONE_URI_STRINGIFY_ARG = "alarm_ringtone_uri_stringify"
    }
}

private fun Uri?.orDefaultRingtone(): Uri =
    this ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
