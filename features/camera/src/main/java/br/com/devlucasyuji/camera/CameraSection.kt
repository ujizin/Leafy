package br.com.devlucasyuji.camera

import android.Manifest
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.ujizin.camposer.CameraPreview
import com.ujizin.camposer.state.rememberCameraState

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun CameraRoute() {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    when (val status = cameraPermissionState.status) {
        PermissionStatus.Granted -> CameraSection()
        is PermissionStatus.Denied -> CameraDenied(status) {
            cameraPermissionState.launchPermissionRequest()
        }
    }
}

@Composable
fun CameraSection() {
    val cameraState = rememberCameraState()
    CameraPreview(
        modifier = Modifier.fillMaxSize(),
        cameraState = cameraState,
    ) {

    }
}
