package br.com.devlucasyuji.local.datasource

import br.com.devlucasyuji.repository.datasource.PhotoDataSource
import br.com.devlucasyuji.repository.model.Photo

internal class PhotoLocalDataSource : PhotoDataSource {

    override suspend fun insertPhoto(photo: Photo) {
        TODO("Not yet implemented")
    }

    override suspend fun insertPhotos(photos: List<Photo>) {
        TODO("Not yet implemented")
    }

    override suspend fun getPhotos(): List<Photo> {
        TODO("Not yet implemented")
    }

    override suspend fun updatePhoto(photo: Photo) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePhoto(photo: Photo) {
        TODO("Not yet implemented")
    }
}