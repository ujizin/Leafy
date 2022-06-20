package br.com.devlucasyuji.local.mapper

import br.com.devlucasyuji.local.model.PhotoEntity
import br.com.devlucasyuji.repository.model.Photo

/**
 * Mapper between [Photo] and [PhotoEntity]
 * */
class PhotoMapper {

    fun toPhoto(photoEntity: PhotoEntity) = with(photoEntity) {
        Photo(id, title, date, filePath, description, favorite, albumId)
    }

    fun toPhotoEntity(photo: Photo) = with(photo) {
        PhotoEntity(id, title, date, filePath, description, favorite, albumId)
    }
}