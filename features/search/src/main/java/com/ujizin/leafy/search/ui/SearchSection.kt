package com.ujizin.leafy.search.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ujizin.leafy.core.ui.components.Section
import com.ujizin.leafy.core.ui.components.animated.AnimatedButtonIcon
import com.ujizin.leafy.core.ui.components.animated.AnimatedIcon
import com.ujizin.leafy.core.ui.components.animated.animation.Animation
import com.ujizin.leafy.core.ui.components.card.CardSize
import com.ujizin.leafy.core.ui.components.image.Icons
import com.ujizin.leafy.core.ui.components.textfield.Placeholder
import com.ujizin.leafy.core.ui.components.textfield.TextField
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.ui.extensions.capitalize
import com.ujizin.leafy.core.ui.extensions.share
import com.ujizin.leafy.core.ui.state.keyboardAsState
import com.ujizin.leafy.features.search.R
import com.ujizin.leafy.search.SearchUiState
import com.ujizin.leafy.search.SearchViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchSection(
    onDrawerClick: OnClick,
    onTakePictureClick: OnClick,
    onScroll: (Boolean) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val isKeyboardOpen by keyboardAsState()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val focusRequester = remember { FocusRequester() }

    val state = rememberLazyStaggeredGridState()
    val isScrolling by remember {
        derivedStateOf {
            (state.canScrollBackward || state.canScrollForward) && state.isScrollInProgress
        }
    }
    var searchText by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(viewModel, searchText) { viewModel.search(searchText) }

    LaunchedEffect(isScrolling) {
        if (!isScrolling) delay(500)
        onScroll(!isScrolling)
    }

    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        state = state,
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalItemSpacing = 4.dp,
    ) {
        item(span = StaggeredGridItemSpan.FullLine) {
            Section(
                modifier = Modifier.fillMaxWidth(),
                headerPaddingValues = PaddingValues(top = 32.dp),
                leadingIcon = if (!isKeyboardOpen) leadingIcon(onDrawerClick) else null,
                title = stringResource(R.string.search).capitalize(),
            )
        }

        item(span = StaggeredGridItemSpan.FullLine) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .focusRequester(focusRequester),
                placeholder = { SearchPlaceholder() },
                value = searchText,
                onValueChange = { searchText = it },
            )
        }

        when (val result: SearchUiState = uiState) {
            is SearchUiState.Initial -> item {
                LaunchedEffect(result.autoFocus) {
                    if (result.autoFocus) focusRequester.requestFocus()
                }

                SearchLoading(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                )
            }

            SearchUiState.Empty -> item(span = StaggeredGridItemSpan.FullLine) {
                SearchEmptyList(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    searchText = searchText,
                    onTakePictureClick = onTakePictureClick,
                )
            }

            is SearchUiState.Loaded -> searchItems(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = CardSize.Small.height, max = CardSize.Large.height),
                data = result.items,
                onSharedClick = { plant ->
                    plant.share(context)
                }
            )
        }

        item(span = StaggeredGridItemSpan.FullLine) {
            Spacer(Modifier.padding(64.dp))
        }
    }
}

private fun leadingIcon(onDrawerClick: OnClick): @Composable () -> Unit = {
    AnimatedButtonIcon(
        icon = Icons.Hamburger,
        animation = Animation.SlideToTop,
        onClick = onDrawerClick,
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
