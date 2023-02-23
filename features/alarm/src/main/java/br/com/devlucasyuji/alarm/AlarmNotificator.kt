package br.com.devlucasyuji.alarm

import android.app.NotificationManager
import android.content.Context
import android.net.Uri
import androidx.core.app.NotificationCompat
import br.com.devlucasyuji.components.R

/**
 * Create alarm notification for Android system.
 * */
object AlarmNotificator {

    private const val CHANNEL_ID = "alarm_channel"

    private val vibrateValues = longArrayOf(800, 500, 600, 300)

    fun show(
        context: Context,
        title: String,
        description: String,
        uri: Uri,
    ) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_monochrome)
            .setContentTitle(title)
            .setContentText(description)
            .setSound(uri)
            .setVibrate(vibrateValues)
            .build()

        val notificationManager = context.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

        notificationManager.notify(1, notification)
    }

}