package com.ujizin.leafy.camera.ui.camera

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.components.extensions.OnClick
import com.ujizin.leafy.components.ui.animated.AnimatedButtonIcon
import com.ujizin.leafy.components.ui.image.Icons

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