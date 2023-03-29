package com.ujizin.leafy.domain.model

import java.io.File
import java.util.UUID

/***
 * Plant Model
 *
 * @param id the plant id
 * @param title the plant title
 * @param file the file path of the plant
 * @param description the plant description
 * @param favorite indicates if plant is favorite
 * @param albumId the album id linked to the plant
 */
data class Plant(
    val id: Long = 0,
    val title: String,
    val description: String,
    val file: File,
    val favorite: Boolean,
    val albumId: Long? = null,
) {
    companion object {
        fun createDraft() = Plant(
            title = "untitled",
            file = File.createTempFile(UUID.randomUUID().toString(), ".jpg"),
            description = "no description",
            favorite = false
        )
    }
}
