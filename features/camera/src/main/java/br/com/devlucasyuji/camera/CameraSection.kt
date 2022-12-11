package br.com.devlucasyuji.camera

import android.Manifest
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.extensions.OnClick
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
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ActionRow(
                onSettingsClicked = {},
                onFlashModeClicked = {},
                onCloseClicked = {},
            )
            PictureButton(modifier = Modifier.padding(24.dp)) {}
        }
    }
}

@Composable
private fun ActionRow(
    onSettingsClicked: OnClick,
    onFlashModeClicked: OnClick,
    onCloseClicked: OnClick
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

    }
}

@Composable
private fun PictureButton(
    modifier: Modifier = Modifier,
    onClick: OnClick
) {
    val interactionSource = remember { MutableInteractionSource() }
    val buttonPressed by interactionSource.collectIsPressedAsState()
    val scaleAnimated by animateFloatAsState(targetValue = if (buttonPressed) 1.05F else 1F)
    Box(
        Modifier
            .then(modifier)
            .scale(scaleAnimated)
            .size(64.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary, CircleShape)
            .border(BorderStroke(8.dp, Color.White), CircleShape)
            .graphicsLayer {
                scaleX = 10F
            }
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(bounded = true),
                onClick = onClick
            )
    )
}
