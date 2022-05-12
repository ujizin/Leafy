package br.com.devlucasyuji.repository.datasource

import br.com.devlucasyuji.repository.model.Photo

interface PhotoDataSource {

    /**
     * Inserts a new photo.
     *
     * @param photo photo to be added
     * */
    suspend fun insertPhoto(photo: Photo)

    /**
     * Inserts a new photo list.
     *
     * @param photos photos' list to be added
     * */
    suspend fun insertPhotos(photos: List<Photo>)

    /**
     * Get all photos.
     *
     * @return all photos added.
     * */
    suspend fun getPhotos(): List<Photo>

    /**
     * Update the photo passed on parameter.
     *
     * @param photo photo to be updated
     * */
    suspend fun updatePhoto(photo: Photo)

    /**
     * Delete the photo passed on parameter.
     *
     * @param photo photo to be deleted
     * */
    suspend fun deletePhoto(photo: Photo)
}