package com.ujizin.leafy.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import com.ujizin.leafy.core.components.R

/**
 * Create alarm notification for Android system.
 * */
object AlarmNotificator {

    private const val CHANNEL_ID = "alarm_channel"

    private val vibrateValues = longArrayOf(800, 500, 600, 300)

    fun getNotification(
        context: Context,
        title: String,
        description: String,
        contentIntent: PendingIntent,
    ) = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_monochrome)
        .setContentTitle(title)
        .setContentText(description)
        .setVibrate(vibrateValues)
        .setAutoCancel(false)
        .setContentIntent(contentIntent)
        .build()

    fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }

        val notificationManager = context.getSystemService(
            Context.NOTIFICATION_SERVICE,
        ) as NotificationManager

        val notificationChannel = NotificationChannel(
            CHANNEL_ID,
            "Leafy alarm", // TODO change name later
            NotificationManager.IMPORTANCE_HIGH
        )

        notificationManager.createNotificationChannel(notificationChannel)
    }
}
