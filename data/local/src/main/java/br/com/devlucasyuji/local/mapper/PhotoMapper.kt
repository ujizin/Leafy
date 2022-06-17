package br.com.devlucasyuji.local.mapper

import br.com.devlucasyuji.local.model.PhotoEntity
import br.com.devlucasyuji.repository.model.Photo

class PhotoMapper {

    fun toPhoto(photoEntity: PhotoEntity) = with(photoEntity) {
        Photo(id, title, date, filePath, description, favorite, albumId)
    }

    fun toPhotoEntity(photo: Photo) = with(photo) {
        PhotoEntity(id, title, date, filePath, description, favorite, albumId)
    }
}
