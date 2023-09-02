package com.ujizin.leafy.alarm.extensions

import android.media.RingtoneManager
import android.net.Uri

internal fun Uri?.orDefaultRingtone(): Uri =
    this ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
