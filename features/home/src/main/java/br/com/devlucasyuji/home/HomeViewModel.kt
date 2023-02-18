package br.com.devlucasyuji.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.devlucasyuji.domain.model.Plant
import br.com.devlucasyuji.domain.result.ifAnyError
import br.com.devlucasyuji.domain.result.ifSuccess
import br.com.devlucasyuji.domain.usecase.plant.LoadAllPlant
import br.com.devlucasyuji.domain.usecase.user.LoadUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class HomeViewModel @Inject constructor(
    loadAllPlant: LoadAllPlant,
    loadUser: LoadUser,
) : ViewModel() {

    val homeState: StateFlow<HomeUIState> = combine(
        loadAllPlant(),
        loadUser(),
    ) { plantResult, userResult ->

        ifAnyError(userResult, plantResult) {
            return@combine HomeUIState.Error(it.first())
        }

        ifSuccess(userResult, plantResult) { user, plants ->
            return@combine HomeUIState.Success(user.nickname, plants)
        }

        return@combine HomeUIState.Loading
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUIState.Loading,
    )

}

sealed interface HomeUIState {
    data class Success(
        val nickname: String,
        val plants: List<Plant>
    ) : HomeUIState

    data class Error(val throwable: Throwable?) : HomeUIState
    object Loading : HomeUIState

}
