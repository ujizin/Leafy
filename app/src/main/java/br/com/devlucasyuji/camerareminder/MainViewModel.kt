package br.com.devlucasyuji.camerareminder

import androidx.lifecycle.ViewModel
import br.com.devlucasyuji.domain.model.User
import br.com.devlucasyuji.domain.result.Result
import br.com.devlucasyuji.domain.usecase.user.LoadUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadUser: LoadUser
) : ViewModel() {

    fun load(): Flow<MainUiState> = loadUser()
        .map { result ->
            when (result) {
                is Result.Success -> MainUiState.Initialized(result.data)
                else -> MainUiState.Loading
            }
        }

}

sealed interface MainUiState {
    object Loading : MainUiState
    data class Initialized(val user: User) : MainUiState
}