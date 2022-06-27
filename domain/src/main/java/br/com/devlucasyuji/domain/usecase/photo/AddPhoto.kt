package br.com.devlucasyuji.domain.usecase.photo

import br.com.devlucasyuji.domain.model.Photo
import kotlinx.coroutines.flow.Flow

/**
 * Use case to insert photo in the data source.
 * */
interface AddPhoto {

    /**
     * Add a photo.
     *
     * @param photos photos to be added
     * */
    operator fun invoke(vararg photos: Photo): Flow<Unit>
}