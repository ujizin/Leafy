package br.com.devlucasyuji.camera

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
internal fun CameraRoute(
    viewModel: CameraViewModel = hiltViewModel(),
    onSaveClicked: (ByteArray) -> Unit,
    onCloseClicked: OnClick
) {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    when (val status = cameraPermissionState.status) {
        PermissionStatus.Granted -> {
            val uiState by viewModel.uiState.collectAsState()
            val state: CameraUiState = uiState
            var previewLoaded by remember { mutableStateOf(false) }
            val cameraState = rememberCameraState()

            if (!previewLoaded) {
                CameraSection(
                    uiState = state,
                    cameraState = cameraState,
                    onCloseClicked = onCloseClicked,
                    onTakePicture = remember { { viewModel.takePicture(cameraState) } }
                )
            }

            if (state is CameraUiState.Preview) {
                CameraPreviewSection(
                    previewImage = state.imageByteArray,
                    onSuccess = { previewLoaded = true },
                    onBackPressed = {
                        previewLoaded = false
                        viewModel.onBackCamera()
                    },
                    onSaveClicked = onSaveClicked
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
