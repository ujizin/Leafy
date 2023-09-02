package com.ujizin.leafy.alarm.extensions

import android.content.Intent
import android.net.Uri
import com.ujizin.leafy.alarm.AlarmService
import com.ujizin.leafy.alarm.receiver.AlarmReceiver

val Intent.plantId get() = getLongExtra(AlarmService.PLANT_ID, -1)

internal val Intent.alarmId get() = getLongExtra(AlarmReceiver.ALARM_ID_EXTRA, -1)

internal val Intent.ringtoneStringify get() = getStringExtra(AlarmReceiver.RINGTONE_CONTENT_EXTRA)

internal val Intent.ringtoneUri get() = ringtoneStringify?.let(Uri::parse).orDefaultRingtone()