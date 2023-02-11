package br.com.devlucasyuji.camera.ui

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
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
    onImageSaved: (filepath: String) -> Unit,
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
                    onTakePicture = { viewModel.takePicture(cameraState) }
                )
            }

            when (state) {
                is CameraUiState.Preview -> {
                    val context = LocalContext.current
                    CameraPreviewSection(
                        previewImage = state.imageByteArray,
                        onSuccess = { previewLoaded = true },
                        onBackPressed = {
                            previewLoaded = false
                            viewModel.onBackCamera()
                        },
                        onSaveClicked = {
                            viewModel.saveImage(context, state.imageByteArray, onImageSaved)
                        }
                    )
                }

                else -> Unit
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
