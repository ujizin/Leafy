package br.com.devlucasyuji.camera.components.camera

import android.content.ContentValues
import android.content.Context
import android.provider.MediaStore
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * CameraState to [CameraPreview] composable
 * */
class CameraState(private val context: Context) {

    /**
     * Camera mode, it can be front or back.
     * @see CameraMode
     * */
    var cameraMode: CameraMode = CameraMode.Back
        set(value) {
            controller.cameraSelector = cameraMode.selector
            field = value
        }

    /**
     * Set scale type from the camera.
     * */
    var scaleType: PreviewView.ScaleType = PreviewView.ScaleType.FIT_CENTER

    /**
     * Set if camera is full screen or not.
     * */
    var isFullScreen: Boolean by mutableStateOf(true)

    /**
     * Flash Mode from the camera.
     * @see FlashMode
     * */
    var flashMode: FlashMode = FlashMode.Off
        set(value) {
            controller.imageCaptureFlashMode = value.mode
            field = value
        }

    /**
     * Get ImageCapture from the camera.
     * */
    internal val imageCapture: ImageCapture = ImageCapture.Builder().build()

    internal val controller by lazy { LifecycleCameraController(context) }

    /**
     *  Take a picture on the camera.
     *
     *  @param onResult Callback called when [PhotoResult] is ready
     * */
    fun takePicture(onResult: (PhotoResult) -> Unit) {
        controller.takePicture(
            createOutputFile(),
            context.mainExecutor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    onResult(PhotoResult.Success)
                }

                override fun onError(exception: ImageCaptureException) {
                    onResult(PhotoResult.Error(exception))
                }
            })
    }

    /**
     * Create output file directory to camera.
     * */
    private fun createOutputFile(): ImageCapture.OutputFileOptions {
        val name = SimpleDateFormat(DATE_FORMAT, Locale.US)
            .format(System.currentTimeMillis())

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, MIME_TYPE)
            put(MediaStore.Images.Media.RELATIVE_PATH, RELATIVE_PATH)
        }

        return ImageCapture.OutputFileOptions
            .Builder(
                context.contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()
    }

    /**
     * Toggle camera, it can be front or back camera
     * */
    fun toggleCamera() {
        cameraMode = when (cameraMode) {
            CameraMode.Front -> CameraMode.Back
            else -> CameraMode.Front
        }
    }

    /**
     * Toggle Flash, it can be on or off, this case auto is ignored.
     * */
    fun toggleFlash() {
        flashMode = when (flashMode) {
            FlashMode.On -> FlashMode.Off
            else -> FlashMode.On
        }
    }

    companion object {
        private const val DATE_FORMAT = "YYYY-HH:MM:SS"
        private const val MIME_TYPE = "image/jpeg"

        // FIXME set app name from the app or searching another way to set name
        private const val RELATIVE_PATH = "Pictures/Camera reminder"
    }
}

@Composable
internal fun rememberCameraState(): CameraState {
    val context = LocalContext.current
    return remember { CameraState(context) }
}