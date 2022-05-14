package br.com.devlucasyuji.local.datasource

import br.com.devlucasyuji.repository.datasource.AlbumDataSource
import br.com.devlucasyuji.repository.model.Album

internal class AlbumLocalDataSource : AlbumDataSource {

    override suspend fun insertAlbum(album: Album) {
        TODO("Not yet implemented")
    }

    override suspend fun insertAlbums(albums: List<Album>) {
        TODO("Not yet implemented")
    }

    override suspend fun getAlbums(): List<Album> {
        TODO("Not yet implemented")
    }

    override suspend fun findAlbum(albumId: String): Album? {
        TODO("Not yet implemented")
    }

    override suspend fun updateAlbum(album: Album) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAlbum(album: Album) {
        TODO("Not yet implemented")
    }
}