package br.com.devlucasyuji.camera

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.ui.EmptySection
import br.com.devlucasyuji.components.ui.image.Icons
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import br.com.devlucasyuji.components.R

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun CameraDenied(status: PermissionStatus.Denied, onCameraRequest: OnClick) {
    var descriptionRes by remember { mutableStateOf(R.string.camera_permission_description) }
    EmptySection(
        modifier = Modifier.fillMaxSize(),
        descriptionRes = descriptionRes,
        buttonRes = R.string.camera_permission_allow,
        icons = Icons.Folder,
        enabled = status.shouldShowRationale,
        onClick = onCameraRequest
    )

    LaunchedEffect(status.shouldShowRationale) {
        if (status.shouldShowRationale) {
            onCameraRequest()
        } else {
            descriptionRes = R.string.camera_permission_denied_description
        }
    }
}
