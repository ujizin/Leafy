package br.com.devlucasyuji.camera.ui.camera

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.ui.animated.AnimatedButtonIcon
import br.com.devlucasyuji.components.ui.image.Icons

@Composable
internal fun CameraHeader(
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