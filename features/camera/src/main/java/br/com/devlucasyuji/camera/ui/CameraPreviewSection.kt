package br.com.devlucasyuji.camera.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.ui.animated.AnimatedIcon
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.components.ui.image.Icons
import coil.compose.AsyncImage

@Composable
internal fun CameraPreviewSection(
    previewImage: ByteArray,
    onSuccess: () -> Unit,
    onBackPressed: OnClick,
) {
    BackHandler(onBack = onBackPressed)
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
        animation = Animation.SlideToBottom,
        onClick = onBackPressed
    )
}
