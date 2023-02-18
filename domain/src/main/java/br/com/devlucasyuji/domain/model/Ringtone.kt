package br.com.devlucasyuji.domain.model

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
    val uri: Uri,
)
