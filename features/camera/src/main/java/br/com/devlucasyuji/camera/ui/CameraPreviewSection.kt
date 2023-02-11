package br.com.devlucasyuji.camera.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.R
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.ui.animated.AnimatedIcon
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.components.ui.button.Button
import br.com.devlucasyuji.components.ui.image.Icons
import coil.compose.AsyncImage

@Composable
internal fun CameraPreviewSection(
    previewImage: ByteArray,
    onSuccess: () -> Unit,
    onSaveClicked: (ByteArray) -> Unit,
    onBackPressed: OnClick,
) {
    BackHandler(onBack = onBackPressed)
    Box(Modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = previewImage,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            onSuccess = { onSuccess() }
        )
        AnimatedIcon(
            modifier = Modifier.padding(16.dp),
            icon = Icons.Back,
            tint = Color.White,
            animation = Animation.SlideToBottom,
            onClick = onBackPressed
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            text = stringResource(id = R.string.save),
            onClick = { onSaveClicked(previewImage) }
        )
    }
}
