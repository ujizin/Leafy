package br.com.devlucasyuji.domain.usecase.photo.implementation

import br.com.devlucasyuji.domain.model.Photo
import br.com.devlucasyuji.domain.repository.PhotoRepository
import br.com.devlucasyuji.domain.usecase.photo.DeletePhoto

class DeletePhotoImpl(
    private val photoRepository: PhotoRepository
): DeletePhoto {

    override fun invoke(photo: Photo) = photoRepository.deletePhoto(photo)

}
