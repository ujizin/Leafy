package br.com.devlucasyuji.camera.ui

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.devlucasyuji.camera.ui.camera.Camera
import br.com.devlucasyuji.camera.viewmodel.CameraUiState
import br.com.devlucasyuji.camera.viewmodel.CameraViewModel
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.extensions.startSettingsPermission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.ujizin.camposer.state.rememberCameraState

@Composable
@OptIn(ExperimentalPermissionsApi::class)
internal fun CameraRoute(
    viewModel: CameraViewModel = hiltViewModel(),
    onImageSaved: () -> Unit,
    onCloseClicked: OnClick
) {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    val context = LocalContext.current
    when (val status = cameraPermissionState.status) {
        PermissionStatus.Granted -> {
            val uiState by viewModel.uiState.collectAsState()
            val state: CameraUiState = uiState
            val cameraState = rememberCameraState()

            if (state !is CameraUiState.Preview) {
                Camera(
                    uiState = state,
                    cameraState = cameraState,
                    onCloseClicked = onCloseClicked,
                    onTakePicture = { viewModel.takePicture(cameraState) }
                )
            }

            when (state) {
                is CameraUiState.Preview -> {
                    CameraPreviewSection(
                        previewImage = state.imageByteArray,
                        onBackPressed = viewModel::onBackCamera,
                        onSaveClicked = {
                            viewModel.saveImage(context, state.imageByteArray, onImageSaved)
                        }
                    )
                }

                else -> Unit
            }
        }

        is PermissionStatus.Denied -> CameraDenied(status.shouldShowRationale) {
            if (status.shouldShowRationale) {
                cameraPermissionState.launchPermissionRequest()
            } else {
                context.startSettingsPermission()
            }
        }
    }

    LaunchedEffect(Unit) {
        cameraPermissionState.launchPermissionRequest()
    }
}
