package com.ujizin.leafy.repository.model

/***
 * Contract Album Model to AlbumDataSource
 *
 * @param id the album id
 * @param title the album title
 * */
data class Album(
    val id: Long = 0,
    val title: String,
)
