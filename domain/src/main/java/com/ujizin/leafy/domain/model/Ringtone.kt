package com.ujizin.leafy.domain.model

import android.content.Context
import android.media.RingtoneManager
import android.net.Uri

/**
 * Ringtone model
 *
 * @param id ringtone's id
 * @param title ringtone's title
 * @param uri ringtone's uri
 * */
data class Ringtone(
    val id: String,
    val title: String,
    val uri: Uri
)

fun Ringtone?.orDefault(context: Context) = this ?: run {
    val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
    val ringtone = RingtoneManager.getRingtone(context, uri)
    Ringtone("0", ringtone.getTitle(context), uri)
}
