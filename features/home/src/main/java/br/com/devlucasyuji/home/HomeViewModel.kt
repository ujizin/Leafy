package br.com.devlucasyuji.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.devlucasyuji.domain.model.Photo
import br.com.devlucasyuji.domain.result.ifAnyError
import br.com.devlucasyuji.domain.result.ifSuccess
import br.com.devlucasyuji.domain.usecase.photo.LoadAllPhoto
import br.com.devlucasyuji.domain.usecase.user.LoadUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class HomeViewModel @Inject constructor(
    loadAllPhoto: LoadAllPhoto,
    loadUser: LoadUser,
) : ViewModel() {

    val homeState: StateFlow<HomeUIState> =
        combine(
            loadAllPhoto(),
            loadUser(),
        ) { photoResult, userResult ->

            ifAnyError(userResult, photoResult) {
                return@combine HomeUIState.Error(it.first())
            }

            ifSuccess(userResult, photoResult) { user, photos ->
                return@combine HomeUIState.Success(user.nickname, photos)
            }

            return@combine HomeUIState.Loading
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUIState.Loading,
        )

    fun takePicture() {
        // TODO navigate to picture section
    }
}

sealed interface HomeUIState {
    data class Success(
        val nickname: String,
        val photos: List<Photo>
    ) : HomeUIState

    data class Error(val throwable: Throwable?) : HomeUIState
    object Loading : HomeUIState
}
