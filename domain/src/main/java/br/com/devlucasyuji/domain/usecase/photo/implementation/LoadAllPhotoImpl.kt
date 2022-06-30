package br.com.devlucasyuji.domain.usecase.photo.implementation

import br.com.devlucasyuji.domain.model.Photo
import br.com.devlucasyuji.domain.repository.PhotoRepository
import br.com.devlucasyuji.domain.usecase.photo.LoadAllPhoto
import kotlinx.coroutines.flow.Flow

internal class LoadAllPhotoImpl(private val repository: PhotoRepository) : LoadAllPhoto {

    override fun invoke(): Flow<List<Photo>> = repository.getPhotos()

}