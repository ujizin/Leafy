package com.ujizin.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.devlucasyuji.domain.model.User
import br.com.devlucasyuji.domain.usecase.user.UpdateUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    private val updateUser: UpdateUser,
) : ViewModel() {

    fun update(user: User) {
        updateUser(user).launchIn(viewModelScope)
    }
}
