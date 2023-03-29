package com.ujizin.leafy.local.mapper

import com.ujizin.leafy.local.model.AlbumEntity
import com.ujizin.leafy.repository.model.Album

/**
 * Mapper between [Album] and [AlbumEntity]
 * */
internal class AlbumMapper {

    fun toAlbumEntity(album: Album) = with(album) {
        AlbumEntity(id, title)
    }

    fun toAlbum(albumEntity: AlbumEntity) = with(albumEntity) {
        Album(id, title)
    }
}
