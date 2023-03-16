package br.com.devlucasyuji.camera.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.devlucasyuji.components.R
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.extensions.capitalize
import br.com.devlucasyuji.components.ui.EmptySection
import br.com.devlucasyuji.components.ui.image.Icons

@Composable
internal fun CameraDenied(shouldShowRationale: Boolean, onCameraRequest: OnClick) {
    val descriptionRes by remember(shouldShowRationale) {
        mutableStateOf(
            when {
                shouldShowRationale -> R.string.camera_permission_description
                else -> R.string.camera_permission_denied_description
            }
        )
    }
    EmptySection(
        modifier = Modifier.fillMaxSize(),
        description = stringResource(descriptionRes).capitalize(),
        buttonTitle = stringResource(R.string.camera_permission_allow).capitalize(),
        icons = Icons.Folder,
        onClick = onCameraRequest
    )
}
