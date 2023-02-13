package br.com.devlucasyuji.camera.ui

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.camera.viewmodel.CameraUiState
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.ui.animated.AnimatedButtonIcon
import br.com.devlucasyuji.components.ui.image.Icons
import com.ujizin.camposer.CameraPreview
import com.ujizin.camposer.state.CameraState

@Composable
internal fun CameraSection(
    uiState: CameraUiState,
    cameraState: CameraState,
    onCloseClicked: OnClick,
    onTakePicture: OnClick,
) {
    if (uiState is CameraUiState.Error) {
        ErrorPopUp(uiState.message)
    }
    var zoomRatio by remember { mutableStateOf(cameraState.minZoom) }

    CameraPreview(
        modifier = Modifier.fillMaxSize(),
        cameraState = cameraState,
        zoomRatio = zoomRatio,
        onZoomRatioChanged = { zoomRatio = it }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ActionRow(
                onSettingsClicked = {},
                onFlashModeClicked = {},
                onCloseClicked = onCloseClicked,
            )
            PictureButton(modifier = Modifier.padding(24.dp), onClick = onTakePicture)
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

@Composable
private fun ActionRow(
    onSettingsClicked: OnClick,
    onFlashModeClicked: OnClick,
    onCloseClicked: OnClick
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box() {}
        Box() {}
        AnimatedButtonIcon(icon = Icons.Close, tint = Color.White, onClick = onCloseClicked)
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
            .size(72.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary, CircleShape)
            .border(BorderStroke(8.dp, Color.White), CircleShape)
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(bounded = true),
                onClick = onClick
            )
    )
}
