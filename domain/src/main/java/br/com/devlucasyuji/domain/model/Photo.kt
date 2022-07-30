package br.com.devlucasyuji.domain.model

import java.io.File

/***
 * Photo Model
 *
 * @param id the photo id
 * @param title the photo title
 * @param date the date of the photo
 * @param file the file path of the photo
 * @param description the photo description
 * @param favorite indicates if photo is favorite
 * @param albumId the album id linked to the photo
 */
data class Photo(
    val id: Long = 0,
    val title: String,
    val date: String,
    val file: File,
    val description: String,
    val favorite: Boolean,
    val albumId: Long? = null,
)
