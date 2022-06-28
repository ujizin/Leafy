package br.com.devlucasyuji.domain.usecase.photo

import br.com.devlucasyuji.domain.model.Photo
import kotlinx.coroutines.flow.Flow

/**
 * Use case to delete photo in the data source.
 * */
interface DeletePhoto {

    /**
     * Delete a photo.
     *
     * @param photo photo to be deleted
     * */
    operator fun invoke(photo: Photo): Flow<Unit>
}