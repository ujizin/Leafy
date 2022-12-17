package br.com.devlucasyuji.camera.viewmodel

import androidx.camera.core.ImageCapture
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ujizin.camposer.extensions.takePicture
import com.ujizin.camposer.state.CameraState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<CameraUiState>(CameraUiState.Initial)
    val uiState: StateFlow<CameraUiState> get() = _uiState

    fun takePicture(cameraState: CameraState) {
        flow {
            val outputStream = ByteArrayOutputStream()
            cameraState.takePicture(ImageCapture.OutputFileOptions.Builder(outputStream).build())
            val byteArray = outputStream.toByteArray()
            emit(byteArray)
        }.catch { exception ->
            _uiState.update { CameraUiState.Error("${exception.message}") }
        }.onEach { bitmap ->
            _uiState.update { CameraUiState.Preview(bitmap) }
        }.launchIn(viewModelScope)
    }

    fun onBackCamera() {
        _uiState.update { CameraUiState.Initial }
    }
}

sealed interface CameraUiState {
    object Initial : CameraUiState
    class Preview(val imageByteArray: ByteArray) : CameraUiState
    data class Error(val message: String) : CameraUiState
}