package com.ujizin.leafy.alarm.extensions

import android.content.Intent
import android.net.Uri
import com.ujizin.leafy.alarm.AlarmService.Companion.ALARM_ID_EXTRA
import com.ujizin.leafy.alarm.AlarmService.Companion.PLANT_ID_EXTRA
import com.ujizin.leafy.alarm.AlarmService.Companion.RINGTONE_URI_STRINGIFY_EXTRA

internal val Intent.plantId
    get() = getLongExtra(PLANT_ID_EXTRA, -1)

internal val Intent.ringtoneUri
    get() = getStringExtra(RINGTONE_URI_STRINGIFY_EXTRA)?.let(Uri::parse).orDefaultRingtone()

internal val Intent.alarmId: Long
    get() = getLongExtra(ALARM_ID_EXTRA, -1)
