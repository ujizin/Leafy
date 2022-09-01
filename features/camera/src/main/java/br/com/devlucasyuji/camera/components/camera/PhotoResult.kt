package br.com.devlucasyuji.camera.components.camera

/**
 * Photo Result of take a picture.
 * */
sealed interface PhotoResult {
    object Success : PhotoResult
    data class Error(val throwable: Throwable) : PhotoResult
}
