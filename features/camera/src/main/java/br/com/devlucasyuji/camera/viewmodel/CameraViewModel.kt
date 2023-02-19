package br.com.devlucasyuji.camera.viewmodel

import android.content.Context
import androidx.camera.core.ImageCapture
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.devlucasyuji.domain.usecase.file.SaveFile
import br.com.devlucasyuji.domain.usecase.plant.AddDraftPlant
import com.ujizin.camposer.extensions.takePicture
import com.ujizin.camposer.state.CameraState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val saveFile: SaveFile,
    private val addDraftPlant: AddDraftPlant,
) : ViewModel() {

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

    fun saveImage(
        context: Context,
        imageByteArray: ByteArray,
        onImageSaved: () -> Unit,
    ) {
        viewModelScope.launch {
            val file = saveFile(context.filesDir, imageByteArray, "jpg")
            addDraftPlant(file = file)
                .onCompletion { onImageSaved() }
                .first()
        }
    }
}

sealed interface CameraUiState {
    object Initial : CameraUiState
    class Preview(val imageByteArray: ByteArray) : CameraUiState
    data class Error(val message: String) : CameraUiState

}