package br.com.devlucasyuji.camera.ui

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
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
            val cameraState = rememberCameraState()
            val launcher = rememberLauncherForActivityResult(PickVisualMedia()) launcher@{ uri ->
                if (uri == null) return@launcher
                viewModel.preparePreview(
                    contentResolver = context.contentResolver,
                    uri = uri,
                )
            }

            when (val state = uiState) {
                is CameraUiState.Preview -> CameraPreviewSection(
                    bitmap = state.bitmap,
                    onBackPressed = viewModel::onBackCamera,
                    onSaveClicked = {
                        viewModel.saveImage(
                            context = context,
                            bitmap = state.bitmap,
                            onImageSaved = onImageSaved
                        )
                    }
                )

                else -> Camera(
                    uiState = state,
                    cameraState = cameraState,
                    onCloseClicked = onCloseClicked,
                    onTakePicture = { viewModel.takePicture(cameraState) },
                    onGalleryClick = {
                        launcher.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
                    }
                )
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
