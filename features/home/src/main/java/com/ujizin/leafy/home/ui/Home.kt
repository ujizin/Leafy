package com.ujizin.leafy.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ujizin.leafy.core.themes.LeafyTheme
import com.ujizin.leafy.core.ui.annotation.ThemePreviews
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.home.HomeUIState
import com.ujizin.leafy.home.HomeViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onTakePictureClick: OnClick,
    onDrawerClick: OnClick,
    onSearchClick: OnClick
) {
    val state by viewModel.homeState.collectAsStateWithLifecycle()

    Home(
        state = state,
        onTakePictureClick = onTakePictureClick,
        onSearchClick = onSearchClick,
        onDrawerClick = onDrawerClick
    )
}

@Composable
private fun Home(
    state: HomeUIState,
    onTakePictureClick: OnClick,
    onSearchClick: OnClick,
    onDrawerClick: OnClick,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        when (val result: HomeUIState = state) {
            HomeUIState.Loading -> {}
            is HomeUIState.Success -> HomeSection(
                nickname = result.nickname,
                plants = result.plants,
                onEmptyPlantClick = onTakePictureClick,
                onSearchClick = onSearchClick,
                onDrawerClick = onDrawerClick,
            )

            is HomeUIState.Error -> {}
        }
    }
}

@ThemePreviews
@Composable
private fun PreviewHome() {
    LeafyTheme {
        Surface {
            Home(
                state = HomeUIState.Success("ujizin", listOf()),
                onTakePictureClick = {},
                onSearchClick = {},
                onDrawerClick = {}
            )
        }
    }
}
