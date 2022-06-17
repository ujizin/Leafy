package br.com.devlucasyuji.local.datasource

import br.com.devlucasyuji.local.dao.AlbumDao
import br.com.devlucasyuji.local.mapper.AlbumMapper
import br.com.devlucasyuji.repository.datasource.AlbumDataSource
import br.com.devlucasyuji.repository.model.Album

internal class AlbumLocalDataSource(
    private val albumDao: AlbumDao,
    private val mapper: AlbumMapper,
) : AlbumDataSource {

    override suspend fun insertAlbum(album: Album) {
        albumDao.insert(mapper.toAlbumEntity(album))
    }

    override suspend fun insertAlbums(albums: List<Album>) {
        val albumEntities = albums.map { mapper.toAlbumEntity(it) }
        albumDao.insert(*albumEntities.toTypedArray())
    }

    override suspend fun getAlbums(): List<Album> = albumDao.getAll().map(mapper::toAlbum)

    override suspend fun findAlbumById(albumId: Long): Album? {
        val albumEntity = albumDao.findAlbumById(albumId) ?: return null
        return mapper.toAlbum(albumEntity)
    }

    override suspend fun updateAlbum(album: Album) {
        albumDao.update(mapper.toAlbumEntity(album))
    }

    override suspend fun deleteAlbum(album: Album) {
        albumDao.delete(mapper.toAlbumEntity(album))
    }
}
