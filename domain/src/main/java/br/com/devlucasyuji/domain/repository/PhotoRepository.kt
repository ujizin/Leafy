package br.com.devlucasyuji.domain.repository

import br.com.devlucasyuji.domain.model.Photo
import kotlinx.coroutines.flow.Flow

/**
 * Interface to Alarm Repository implementation.
 * */
interface PhotoRepository {

    /**
     * Get all Photos.
     *
     * @return list of [Photo]
     * */
    fun getPhotos(): Flow<List<Photo>>

    /**
     * Insert Photo.
     *
     * @param photo photo to be added
     * */
    fun insertPhoto(photo: Photo): Flow<Unit>

    /**
     * Insert a list of Photo.
     *
     * @param photos list of photo to be added
     * */
    fun insertPhotos(photos: List<Photo>): Flow<Unit>

    /**
     * Update Photo.
     *
     * @param photo photo to be updated
     * */
    fun updatePhoto(photo: Photo): Flow<Unit>

    /**
     * Delete Photo.
     *
     * @param photo photo to be deleted
     * */
    fun deletePhoto(photo: Photo): Flow<Unit>
}
