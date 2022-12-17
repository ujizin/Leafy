package br.com.devlucasyuji.camera

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.devlucasyuji.camera.ui.CameraDenied
import br.com.devlucasyuji.camera.ui.CameraPreviewSection
import br.com.devlucasyuji.camera.ui.CameraSection
import br.com.devlucasyuji.camera.viewmodel.CameraUiState
import br.com.devlucasyuji.camera.viewmodel.CameraViewModel
import br.com.devlucasyuji.components.extensions.OnClick
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.ujizin.camposer.state.rememberCameraState

@Composable
@OptIn(ExperimentalPermissionsApi::class)
internal fun CameraRoute(viewModel: CameraViewModel = hiltViewModel(), onCloseClicked: OnClick) {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    when (val status = cameraPermissionState.status) {
        PermissionStatus.Granted -> {
            val uiState by viewModel.uiState.collectAsState()
            when (val state: CameraUiState = uiState) {
                CameraUiState.Initial, is CameraUiState.Error -> {
                    val cameraState = rememberCameraState()
                    CameraSection(
                        uiState = state,
                        cameraState = cameraState,
                        onCloseClicked = onCloseClicked,
                        onTakePicture = remember { { viewModel.takePicture(cameraState) } }
                    )
                }

                is CameraUiState.Preview -> CameraPreviewSection(
                    previewImage = state.imageByteArray,
                    viewModel::onBackCamera
                )
            }
        }

        is PermissionStatus.Denied -> CameraDenied(status) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    LaunchedEffect(Unit) {
        cameraPermissionState.launchPermissionRequest()
    }
}
