package br.com.devlucasyuji.camera.components.camera

import java.io.File

/**
 * Photo Result of take a picture.
 * */
sealed interface PhotoResult {
    data class Success(val file: File) : PhotoResult
    data class Error(val throwable: Throwable) : PhotoResult
}
