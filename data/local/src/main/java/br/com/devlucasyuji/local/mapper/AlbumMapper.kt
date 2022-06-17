package br.com.devlucasyuji.local.mapper

import br.com.devlucasyuji.local.model.AlbumEntity
import br.com.devlucasyuji.repository.model.Album

internal class AlbumMapper {

    fun toAlbumEntity(album: Album) = with(album) {
        AlbumEntity(id, title)
    }

    fun toAlbum(albumEntity: AlbumEntity) = with(albumEntity) {
        Album(id, title)
    }
}
