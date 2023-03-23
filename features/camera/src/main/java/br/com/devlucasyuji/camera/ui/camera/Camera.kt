package br.com.devlucasyuji.camera.ui.camera

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.camera.viewmodel.CameraUiState
import br.com.devlucasyuji.components.extensions.OnClick
import com.ujizin.camposer.CameraPreview
import com.ujizin.camposer.state.CameraState
import com.ujizin.camposer.state.rememberCamSelector

@Composable
internal fun Camera(
    uiState: CameraUiState,
    cameraState: CameraState,
    onCloseClicked: OnClick,
    onTakePicture: OnClick,
    onGalleryClick: OnClick,
) {
    if (uiState is CameraUiState.Error) {
        ErrorPopUp(uiState.message)
    }

    var zoomRatio by remember { mutableStateOf(cameraState.minZoom) }
    var camSelector by rememberCamSelector()

    CameraPreview(
        modifier = Modifier.fillMaxSize(),
        cameraState = cameraState,
        camSelector = camSelector,
        zoomRatio = zoomRatio,
        onZoomRatioChanged = { zoomRatio = it }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CameraHeader(
                onSettingsClicked = {},
                onFlashModeClicked = {},
                onCloseClicked = onCloseClicked,
            )
            CameraFooter(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp, vertical = 32.dp),
                onTakePicture = onTakePicture,
                onGalleryClick = onGalleryClick,
                onSwitchClick = { camSelector = camSelector.inverse }
            )
        }
    }
}

@Composable
private fun ErrorPopUp(message: String) {
    val context = LocalContext.current
    LaunchedEffect(message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
