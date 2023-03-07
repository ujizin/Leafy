package br.com.devlucasyuji.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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

@Composable
fun SearchSection(
    onDrawerClick: OnClick,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val isKeyboardOpen by keyboardAsState()
    val leadingIcon = movableContentOf {
        AnimatedButtonIcon(
            icon = Icons.Hamburger,
            animation = Animation.SlideToTop,
            onClick = onDrawerClick
        )
    }
    val trailingIcon = movableContentOf {
        AnimatedButtonIcon(icon = Icons.Settings, animation = Animation.SlideToTop)
    }

    Section(
        modifier = Modifier.fillMaxSize(),
        leadingIcon = if (!isKeyboardOpen) leadingIcon else null,
        trailingIcon = if (!isKeyboardOpen) trailingIcon else null,
        title = stringResource(R.string.search).capitalize()
    ) {
        val uiState by viewModel.searchUiState.collectAsState()
        var searchText by remember { mutableStateOf("") }

        LaunchedEffect(searchText) { viewModel.search(searchText) }

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp),
            placeholder = {
                Placeholder(
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
            },
            value = searchText,
            onValueChange = { searchText = it }
        )

        when (val result: SearchUiState = uiState) {
            SearchUiState.Initial -> Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center)
                )
            }

            is SearchUiState.Loaded -> {}
            is SearchUiState.Success -> {
                LazyColumn {
                    items(result.data) {
                        Text(text = it.title)
                    }
                }
            }


        }
    }
}