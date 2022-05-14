package br.com.devlucasyuji.repository.model

/***
 * Contract Album Model to AlbumDataSource
 *
 * @param id the album id
 * @param photos the photos from album
 * */
data class Album(
    val id: Long = 0,
    val photos: List<Photo> = emptyList()
)
