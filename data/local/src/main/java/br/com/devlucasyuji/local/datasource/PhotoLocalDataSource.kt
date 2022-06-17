package br.com.devlucasyuji.local.datasource

import br.com.devlucasyuji.local.dao.PhotoDao
import br.com.devlucasyuji.local.mapper.PhotoMapper
import br.com.devlucasyuji.repository.datasource.PhotoDataSource
import br.com.devlucasyuji.repository.model.Photo

internal class PhotoLocalDataSource(
    private val photoDao: PhotoDao,
    private val mapper: PhotoMapper,
) : PhotoDataSource {

    override suspend fun insertPhoto(photo: Photo) {
        photoDao.insert(mapper.toPhotoEntity(photo))
    }

    override suspend fun insertPhotos(photos: List<Photo>) {
        val photosEntities = photos.map { mapper.toPhotoEntity(it) }.toTypedArray()
        photoDao.insert(*photosEntities)
    }

    override suspend fun getPhotos(): List<Photo> = photoDao.getAll().map(mapper::toPhoto)

    override suspend fun updatePhoto(photo: Photo) {
        photoDao.update(mapper.toPhotoEntity(photo))
    }

    override suspend fun deletePhoto(photo: Photo) {
        photoDao.delete(mapper.toPhotoEntity(photo))
    }
}