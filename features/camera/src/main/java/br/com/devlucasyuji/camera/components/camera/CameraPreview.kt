package br.com.devlucasyuji.camera.components.camera

import android.view.ViewGroup
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import br.com.devlucasyuji.components.extensions.Content
import hideSystemUi
import showSystemUi

/**
 * Based on Medium post by @dpisoni
 *
 * https://medium.com/@dpisoni/building-a-simple-photo-app-with-jetpack-compose-camerax-and-coroutines-part-2-camera-preview-cf1d795129f6
 * */
@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    cameraState: CameraState = rememberCameraState(),
    content: @Composable Content = {},
) {
    val coroutineScope = rememberCoroutineScope()
    val lifecycleOwner = LocalLifecycleOwner.current
    val isFullScreen by rememberUpdatedState(cameraState.isFullScreen)

    AndroidView(
        modifier = modifier,
        factory = { context ->
            val previewView = PreviewView(context).apply {
                scaleType = cameraState.scaleType
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }

            previewView.controller = cameraState.controller.apply {
                bindToLifecycle(lifecycleOwner)
            }

            previewView
        }
    )

    if (isFullScreen) {
        val context = LocalContext.current
        DisposableEffect(context) {
            context.hideSystemUi()
            onDispose { context.showSystemUi() }
        }
    }
    Box(modifier) { content() }
}
