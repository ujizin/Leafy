package br.com.devlucasyuji.domain.model

import java.io.File

/***
 * Plant Model
 *
 * @param id the plant id
 * @param title the plant title
 * @param date the date of the plant
 * @param file the file path of the plant
 * @param description the plant description
 * @param favorite indicates if plant is favorite
 * @param albumId the album id linked to the plant
 */
data class Plant(
    val id: Long = 0,
    val title: String,
    val date: String,
    val file: File,
    val description: String,
    val favorite: Boolean,
    val albumId: Long? = null,
)
