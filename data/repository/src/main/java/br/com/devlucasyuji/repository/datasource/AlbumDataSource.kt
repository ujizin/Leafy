package br.com.devlucasyuji.repository.datasource

import br.com.devlucasyuji.repository.model.Album

interface AlbumDataSource {

    /**
     * Insert a new album.
     *
     * @param album album to be added
     * */
    suspend fun insertAlbum(album: Album)

    /**
     * Insert a new album list.
     *
     * @param albums albums' list to be added
     * */
    suspend fun insertAlbums(albums: List<Album>)

    /**
     * Get all albums.
     *
     * @return all albums added
     * */
    suspend fun getAlbums(): List<Album>

    /**
     * Find album by id.
     *
     * @param albumId id's album
     * @return album found or null
     * */
    suspend fun findAlbum(albumId: String): Album?

    /**
     * Update album passed on parameter
     *
     * @param album album to be updated
     * */
    suspend fun updateAlbum(album: Album)

    /**
     * Delete album passed on parameter
     *
     * @param album album to be deleted
     * */
    suspend fun deleteAlbum(album: Album)
}