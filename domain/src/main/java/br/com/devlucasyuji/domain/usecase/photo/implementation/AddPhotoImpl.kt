package br.com.devlucasyuji.domain.usecase.photo.implementation

import br.com.devlucasyuji.domain.model.Photo
import br.com.devlucasyuji.domain.repository.PhotoRepository
import br.com.devlucasyuji.domain.usecase.photo.AddPhoto

internal class AddPhotoImpl(
    private val photoRepository: PhotoRepository,
) : AddPhoto {

    override fun invoke(vararg photos: Photo) = photoRepository.insertPhotos(photos.toList())
}