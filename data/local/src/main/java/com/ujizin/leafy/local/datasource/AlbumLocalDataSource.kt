package com.ujizin.leafy.local.datasource

import com.ujizin.leafy.local.dao.AlbumDao
import com.ujizin.leafy.local.mapper.AlbumMapper
import com.ujizin.leafy.repository.datasource.AlbumDataSource
import com.ujizin.leafy.repository.model.Album

internal class AlbumLocalDataSource(
    private val albumDao: AlbumDao,
    private val mapper: AlbumMapper
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
