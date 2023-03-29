package com.ujizin.leafy.domain.model

import android.net.Uri

/***
 *  Alarm Model
 *
 *  @param id the alarm id
 *  @param plantId the plant's id
 *  @param ringtoneUri the alarm's ringtone
 *  @param repeatIntervalInMillis the alarm's repeat interval timer
 *  @param hours the alarm's hours
 *  @param minutes the alarm's minutes
 * */
data class Alarm(
    val id: Long = 0,
    val plantId: Long,
    val ringtoneUri: Uri,
    val repeatIntervalInMillis: Long,
    val hours: Int,
    val minutes: Int,
)
