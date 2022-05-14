package br.com.devlucasyuji.repository.datasource

import br.com.devlucasyuji.repository.model.Photo

interface PhotoDataSource {

    /**
     * Inserts a new [Photo].
     *
     * @param photo [Photo] to be added
     * */
    suspend fun insertPhoto(photo: Photo)

    /**
     * Inserts a new list of [Photo].
     *
     * @param photos list of [Photo] to be added
     * */
    suspend fun insertPhotos(photos: List<Photo>)

    /**
     * Get all [Photo] from data source.
     *
     * @return all [Photo] added.
     * */
    suspend fun getPhotos(): List<Photo>

    /**
     * Update the [Photo] passed on parameter.
     *
     * @param photo [Photo] to be updated
     * */
    suspend fun updatePhoto(photo: Photo)

    /**
     * Delete the photo passed on parameter.
     *
     * @param photo photo to be deleted
     * */
    suspend fun deletePhoto(photo: Photo)
}