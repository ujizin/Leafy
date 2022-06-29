package br.com.devlucasyuji.domain.usecase.photo

import br.com.devlucasyuji.domain.model.Photo
import kotlinx.coroutines.flow.Flow

/**
 * Use case to update photo in the data source.
 * */
interface UpdatePhoto {

    /**
     * Update a photo.
     *
     * @param photo photo to be updated
     * */
    operator fun invoke(photo: Photo): Flow<Unit>
}