package br.com.devlucasyuji.repository.mapper

import br.com.devlucasyuji.domain.model.Photo
import br.com.devlucasyuji.repository.model.Photo as DataPhoto

internal class PhotoMapper {

    fun toDomain(photos: List<DataPhoto>) = photos.map { toDomain(it) }

    fun toDomain(photo: DataPhoto) = with(photo) {
        Photo(id, title, date, filePath, description, favorite, albumId)
    }

    fun toRepo(photo: Photo) = with(photo) {
        DataPhoto(id, title, date, filePath, description, favorite, albumId)
    }
}
