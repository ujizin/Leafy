package br.com.devlucasyuji.search.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.extensions.capitalize
import br.com.devlucasyuji.components.state.keyboardAsState
import br.com.devlucasyuji.components.ui.Section
import br.com.devlucasyuji.components.ui.animated.AnimatedButtonIcon
import br.com.devlucasyuji.components.ui.animated.AnimatedIcon
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.components.ui.image.Icons
import br.com.devlucasyuji.components.ui.textfield.Placeholder
import br.com.devlucasyuji.components.ui.textfield.TextField
import br.com.devlucasyuji.search.R
import br.com.devlucasyuji.search.SearchUiState
import br.com.devlucasyuji.search.SearchViewModel

@Composable
fun SearchSection(
    onDrawerClick: OnClick,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val isKeyboardOpen by keyboardAsState()

    Section(
        modifier = Modifier.fillMaxSize(),
        leadingIcon = if (!isKeyboardOpen) leadingIcon(onDrawerClick) else null,
        trailingIcon = if (!isKeyboardOpen) trailingIcon() else null,
        title = stringResource(R.string.search).capitalize()
    ) {
        val uiState by viewModel.searchUiState.collectAsState()
        var searchText by remember { mutableStateOf("") }

        LaunchedEffect(viewModel) { viewModel.getPlants() }
        LaunchedEffect(searchText) { viewModel.search(searchText) }

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp),
            placeholder = { SearchPlaceholder() },
            value = searchText,
            onValueChange = { searchText = it }
        )

        when (val result: SearchUiState = uiState) {
            SearchUiState.Initial -> SearchLoading(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            SearchUiState.Empty -> SearchEmptyList()
            is SearchUiState.Loaded -> SearchList(result.items, isKeyboardOpen)
        }
    }
}

private fun trailingIcon() = movableContentOf {
    AnimatedButtonIcon(icon = Icons.Settings, animation = Animation.SlideToTop)
}

private fun leadingIcon(onDrawerClick: OnClick) = movableContentOf {
    AnimatedButtonIcon(
        icon = Icons.Hamburger,
        animation = Animation.SlideToTop,
        onClick = onDrawerClick
    )
}

@Composable
private fun SearchPlaceholder(modifier: Modifier = Modifier) {
    Placeholder(
        modifier = modifier,
        leadingIcon = {
            AnimatedIcon(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(16.dp),
                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5F),
                icon = Icons.Magnifier,
            )
        },
        text = stringResource(id = R.string.search).capitalize(),
    )
}
