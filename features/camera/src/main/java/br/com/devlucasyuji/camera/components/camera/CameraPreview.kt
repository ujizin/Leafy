package br.com.devlucasyuji.camera.components.camera

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.camera.lifecycle.ProcessCameraProvider
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
import androidx.lifecycle.LifecycleOwner
import br.com.devlucasyuji.components.extensions.Content
import executor
import hideSystemUi
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.launch
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
    val cameraSelector by rememberUpdatedState(cameraState.cameraSelector)
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

            coroutineScope.launch { lifecycleOwner.bindCamera(previewView, cameraState) }

            previewView
        },
        update = { previewView ->
            coroutineScope.launch { lifecycleOwner.bindCamera(previewView, cameraState) }
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

suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine { continuation ->
    ProcessCameraProvider.getInstance(this).also { future ->
        future.addListener({
            continuation.resume(future.get())
        }, executor)
    }
}

private val PreviewView.previewUseCase: UseCase
    get() = Preview.Builder()
        .build()
        .also {
            it.setSurfaceProvider(surfaceProvider)
        }

private suspend fun LifecycleOwner.bindCamera(
    previewView: PreviewView,
    cameraState: CameraState,
) {
    val cameraProvider = previewView.context.getCameraProvider()
    try {
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            this,
            cameraState.cameraSelector,
            previewView.previewUseCase,
            cameraState.imageCapture
        )
    } catch (ex: Exception) {
        Log.e("CameraPreview", "Use case binding failed", ex)
    }
}