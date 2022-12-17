package br.com.devlucasyuji.camera.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import br.com.devlucasyuji.components.extensions.OnClick
import coil.compose.AsyncImage

@Composable
internal fun CameraPreviewSection(previewImage: ByteArray, onBackPressed: OnClick) {
    BackHandler(onBack = onBackPressed)
    AsyncImage(
        modifier = Modifier.fillMaxSize(),
        model = previewImage,
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}
