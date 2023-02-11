package br.com.devlucasyuji.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.extensions.capitalize
import br.com.devlucasyuji.components.extensions.section
import br.com.devlucasyuji.components.ui.EmptySection
import br.com.devlucasyuji.components.ui.Section
import br.com.devlucasyuji.components.ui.animated.AnimatedIcon
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.components.ui.card.BoxImage
import br.com.devlucasyuji.components.ui.card.CardSize
import br.com.devlucasyuji.components.ui.image.Icons
import br.com.devlucasyuji.components.ui.label.TitleRow
import br.com.devlucasyuji.domain.model.Photo

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onTakePictureClick: OnClick
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val state by viewModel.homeState.collectAsState()
        when (val result: HomeUIState = state) {
            HomeUIState.Loading -> {}
            is HomeUIState.Success -> HomeScreen(result, onTakePictureClick)
            is HomeUIState.Error -> {}
        }
    }
}

@Composable
private fun HomeScreen(result: HomeUIState.Success, onEmptyPhotoClick: OnClick) {
    NavLazyColumn {
        item {
            Section(
                modifier = Modifier.section(),
                title = stringResource(
                    id = R.string.hello_user,
                    result.nickname.capitalize()
                ).capitalize(),
                subTitle = stringResource(id = R.string.welcome_back).capitalize(),
                leadingIcon = {
                    AnimatedIcon(icon = Icons.Hamburger, animation = Animation.SlideToEnd)
                },
                trailingIcon = {
                    AnimatedIcon(icon = Icons.Magnifier, animation = Animation.SlideToStart)
                }
            )
        }
        when {
            result.photos.isEmpty() -> item {
                EmptySection(
                    modifier = Modifier.padding(vertical = 32.dp, horizontal = 20.dp),
                    onClick = onEmptyPhotoClick
                )
            }
            else -> items(result.photos) { HomePhotoCard(it) }
        }
    }
}

@Composable
fun NavLazyColumn(modifier: Modifier = Modifier, content: LazyListScope.() -> Unit) {
    LazyColumn {
        item { Spacer(modifier = modifier) }
        content()
        item { Spacer(Modifier.size(32.dp)) }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun LazyItemScope.HomePhotoCard(photo: Photo) {
    BoxImage(
        modifier = Modifier
            .animateItemPlacement()
            .height(CardSize.Large.height)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        data = photo.file,
        contentDescription = photo.description,
    ) {
        TitleRow(
            title = photo.title,
            titleStyle = MaterialTheme.typography.titleLarge.copy(color = Color.White),
            subTitleStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            subTitle = photo.description,
            verticalAlignment = Alignment.Bottom
        ) {
            AnimatedIcon(icon = Icons.Shared, tint = Color.White, size = 24.dp)
        }
    }
}
