package br.com.devlucasyuji.domain.usecase.photo

import br.com.devlucasyuji.domain.model.Photo
import br.com.devlucasyuji.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow

class LoadAllPhotoImpl(private val repository: PhotoRepository) : LoadAllPhoto {

    override fun invoke(): Flow<List<Photo>> = repository.getPhotos()

}