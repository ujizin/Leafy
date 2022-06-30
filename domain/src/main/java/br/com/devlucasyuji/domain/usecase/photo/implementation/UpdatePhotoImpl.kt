package br.com.devlucasyuji.domain.usecase.photo.implementation

import br.com.devlucasyuji.domain.model.Photo
import br.com.devlucasyuji.domain.repository.PhotoRepository
import br.com.devlucasyuji.domain.usecase.photo.UpdatePhoto
import kotlinx.coroutines.flow.Flow

internal class UpdatePhotoImpl(
    private val photoRepository: PhotoRepository,
) : UpdatePhoto {

    override fun invoke(photo: Photo): Flow<Unit> = photoRepository.updatePhoto(photo)

}
