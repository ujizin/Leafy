package br.com.devlucasyuji.domain.usecase.photo

import br.com.devlucasyuji.domain.model.Photo
import kotlinx.coroutines.flow.Flow

/**
 * Use case to load all photo from repository.
 * */
interface LoadAllPhoto {

    /**
     * Load all photos.
     *
     * @return list of all photos
     * */
    operator fun invoke(): Flow<List<Photo>>
}