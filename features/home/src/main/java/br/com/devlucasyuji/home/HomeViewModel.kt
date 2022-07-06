package br.com.devlucasyuji.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.devlucasyuji.domain.model.Photo
import br.com.devlucasyuji.domain.usecase.photo.LoadAllPhoto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class HomeViewModel @Inject constructor(
    loadAllPhoto: LoadAllPhoto,
) : ViewModel() {

    val photoState: StateFlow<UIState> = combine(loadAllPhoto()) { (photoResult) ->
        if (photoResult.isSuccess) {
            val photos = photoResult.getOrDefault(emptyList())
            // TODO get name from user data store
            UIState.Success("user", photos)
        } else {
            UIState.Error(photoResult.exceptionOrNull())
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        initialValue = UIState.Loading,
    )
}

sealed interface UIState {
    data class Success(
        val nickname: String,
        val photos: List<Photo>
    ) : UIState
    data class Error(val throwable: Throwable?) : UIState
    object Loading : UIState
}