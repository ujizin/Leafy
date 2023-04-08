package com.ujizin.leafy

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ujizin.leafy.domain.model.Theme
import com.ujizin.leafy.domain.model.User

@Composable
fun rememberUser(viewModel: MainViewModel = hiltViewModel()): State<User> {
    val uiState by remember(viewModel) {
        viewModel.load()
    }.collectAsStateWithLifecycle(initialValue = MainUiState.Loading)

    return remember {
        derivedStateOf {
            when (val result: MainUiState = uiState) {
                is MainUiState.Initialized -> result.user
                MainUiState.Loading -> User.default
            }
        }
    }
}

@Composable
fun User.isUserInDarkTheme(): Boolean {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    return remember(this) {
        when (settings.theme) {
            Theme.System -> isSystemInDarkTheme
            Theme.Dark -> true
            Theme.Light -> false
        }
    }
}
