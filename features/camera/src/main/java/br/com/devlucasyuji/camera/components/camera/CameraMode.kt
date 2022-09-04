package br.com.devlucasyuji.camera.components.camera

import androidx.camera.core.CameraSelector

/**
 * Side from Camera.
 *
 * @param selector Camera selector by CameraX
 * */
enum class CameraMode(
    internal var selector: CameraSelector
) {
    Front(CameraSelector.DEFAULT_FRONT_CAMERA),
    Back(CameraSelector.DEFAULT_BACK_CAMERA)
}