package br.com.devlucasyuji.repository.implementation

import br.com.devlucasyuji.domain.model.Photo
import br.com.devlucasyuji.domain.repository.PhotoRepository
import br.com.devlucasyuji.repository.datasource.PhotoDataSource
import br.com.devlucasyuji.repository.mapper.PhotoMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class PhotoRepositoryImpl(
    private val dataSource: PhotoDataSource,
    private val mapper: PhotoMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PhotoRepository {

    override fun getPhotos(): Flow<List<Photo>> = flow {
        emit(mapper.toDomain(dataSource.getPhotos()))
    }.flowOn(dispatcher)

    override fun insertPhoto(photo: Photo) = flow {
        emit(dataSource.insertPhoto(mapper.toRepo(photo)))
    }.flowOn(dispatcher)

    override fun insertPhotos(photos: List<Photo>) = flow {
        emit(dataSource.insertPhotos(mapper.toRepo(photos)))
    }.flowOn(dispatcher)

    override fun updatePhoto(photo: Photo) = flow {
        emit(dataSource.updatePhoto(mapper.toRepo(photo)))
    }.flowOn(dispatcher)

    override fun deletePhoto(photo: Photo) = flow {
        emit(dataSource.deletePhoto(mapper.toRepo(photo)))
    }.flowOn(dispatcher)
}