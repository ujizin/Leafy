package com.ujizin.leafy.camera.ui

import android.graphics.Bitmap
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.shared.components.R
import com.ujizin.leafy.components.extensions.OnClick
import com.ujizin.leafy.components.ui.animated.animation.Animation
import coil.compose.AsyncImage
import com.ujizin.leafy.components.ui.animated.AnimatedButtonIcon
import com.ujizin.leafy.components.ui.button.Button
import com.ujizin.leafy.components.ui.image.Icons

@Composable
internal fun CameraPreviewSection(
    bitmap: Bitmap,
    onSaveClicked: (Bitmap) -> Unit,
    onBackPressed: OnClick,
) {
    BackHandler(onBack = onBackPressed)
    Box(Modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = bitmap,
            contentDescription = null
        )
        AnimatedButtonIcon(
            modifier = Modifier.padding(16.dp),
            icon = Icons.Back,
            animation = Animation.SlideToBottom,
            onClick = onBackPressed
        )
        Button(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            text = stringResource(id = R.string.save),
            onClick = { onSaveClicked(bitmap) }
        )
    }
}
