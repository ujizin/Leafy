package br.com.devlucasyuji.camera.components.camera

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.CameraController
import androidx.camera.view.PreviewView
import androidx.camera.view.PreviewView.ScaleType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.io.File

/**
 * CameraState to [CameraPreview] composable
 * */
interface CameraState {

    /***
     * Selector from the camera.
     * */
    val cameraSelector: CameraSelector

    /**
     * Set if camera is full screen or not.
     * */
    val isFullScreen: Boolean

    /**
     * Set scale type from the camera.
     * */
    val scaleType: PreviewView.ScaleType

    /**
     *  Set Camera controller from CameraX to state.
     *
     *  @param cameraController Camera Controller from camera view
     * */
    fun setCameraController(cameraController: CameraController?)

    /**
     *  Take a picture on the camera.
     *  @param outputDir The directory which the photo will be saved
     *  @param onResult Callback called when [PhotoResult] is ready
     * */
    fun takePicture(outputDir: File, onResult: (PhotoResult) -> Unit)

    /**
     * Pause the camera.
     *
     * @return if it's been paused or not
     * */
    fun pause(): Boolean

    /**
     * Resume the camera.
     *
     * @return if it's been resumed or not
     * */
    fun resume(): Boolean

    /**
     * Turn to the back camera.
     * */
    fun turnBackCamera()

    /**
     * Turn to the front camera.
     * */
    fun turnToFrontCamera()

    /**
     * Set fullscreen mode to the camera.
     *
     * @param fullscreen if it's true then set full screen.
     * */
    fun setFullScreen(fullscreen: Boolean)

    /**
     * Set scale type to the camera.
     *
     * @param scaleType scale type that will be applied
     * */
    fun setScaleType(scaleType: ScaleType)
}

private class CameraStateImpl(
    private val context: Context,
    private var cameraController: CameraController?
) : CameraState {

    private var _cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    override val cameraSelector: CameraSelector
        get() = _cameraSelector

    private var _scaleType: PreviewView.ScaleType = PreviewView.ScaleType.FILL_CENTER
    override val scaleType: PreviewView.ScaleType
        get() = _scaleType

    private var _isFullScreen: Boolean = true
    override val isFullScreen: Boolean
        get() = _isFullScreen

    override fun setCameraController(cameraController: CameraController?) {
        this.cameraController = cameraController
    }

    override fun takePicture(outputDir: File, onResult: (PhotoResult) -> Unit) {
        val output = ImageCapture.OutputFileOptions.Builder(outputDir).build()
        cameraController?.takePicture(
            output,
            context.mainExecutor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    onResult(PhotoResult.Success(outputDir))
                }

                override fun onError(exception: ImageCaptureException) {
                    onResult(PhotoResult.Error(exception))
                }
            })
    }

    override fun pause(): Boolean {
        TODO("Not yet implemented")
    }

    override fun resume(): Boolean {
        TODO("Not yet implemented")
    }

    override fun turnBackCamera() {
        _cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    }

    override fun turnToFrontCamera() {
        _cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
    }

    override fun setFullScreen(fullscreen: Boolean) {
        _isFullScreen = fullscreen
    }

    override fun setScaleType(scaleType: ScaleType) {
        _scaleType = scaleType
    }
}

@Composable
fun rememberCameraState(): State<CameraState> {
    val context = LocalContext.current
    return remember { mutableStateOf(CameraStateImpl(context, null)) }
}