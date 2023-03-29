package com.ujizin.leafy.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.ujizin.leafy.components.extensions.OnClick
import com.ujizin.leafy.components.extensions.capitalize
import com.ujizin.leafy.components.ui.EmptySection
import com.ujizin.leafy.components.ui.Section
import com.ujizin.leafy.components.ui.animated.AnimatedButtonIcon
import com.ujizin.leafy.components.ui.animated.animation.Animation
import com.ujizin.leafy.components.ui.card.BoxImage
import com.ujizin.leafy.components.ui.image.Icons
import com.ujizin.leafy.components.ui.card.CardSize
import com.ujizin.leafy.components.ui.label.TitleRow
import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.features.home.R

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onTakePictureClick: OnClick,
    onDrawerClick: OnClick,
    onSearchClick: OnClick
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val state by viewModel.homeState.collectAsState()
        when (val result: HomeUIState = state) {
            HomeUIState.Loading -> {}
            is HomeUIState.Success -> HomeScreen(
                result = result,
                onEmptyPlantClick = onTakePictureClick,
                onSearchClick = onSearchClick,
                onDrawerClick = onDrawerClick,
            )

            is HomeUIState.Error -> {}
        }
    }
}

@Composable
private fun HomeScreen(
    result: HomeUIState.Success,
    onEmptyPlantClick: OnClick,
    onSearchClick: OnClick,
    onDrawerClick: OnClick
) {
    NavLazyColumn {
        item {
            Section(
                title = stringResource(
                    id = R.string.hello_user,
                    result.nickname.capitalize()
                ).capitalize(),
                subTitle = stringResource(id = R.string.welcome_back).capitalize(),
                leadingIcon = {
                    AnimatedButtonIcon(
                        icon = Icons.Hamburger,
                        animation = Animation.SlideToEnd,
                        onClick = onDrawerClick
                    )
                },
                trailingIcon = {
                    AnimatedButtonIcon(
                        icon = Icons.Magnifier,
                        animation = Animation.SlideToStart,
                        onClick = onSearchClick,
                    )
                }
            )
        }
        when {
            result.plants.isEmpty() -> item {
                EmptySection(
                    modifier = Modifier.padding(vertical = 32.dp, horizontal = 20.dp),
                    onClick = onEmptyPlantClick
                )
            }

            else -> items(result.plants, key = { it.id }) { HomePlantCard(it) }
        }
    }
}

@Composable
fun NavLazyColumn(content: LazyListScope.() -> Unit) {
    LazyColumn {
        content()
        item { Spacer(Modifier.padding(64.dp)) }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun LazyItemScope.HomePlantCard(plant: Plant) {
    BoxImage(
        modifier = Modifier
            .animateItemPlacement()
            .height(CardSize.Medium.height)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        data = plant.file,
        contentDescription = plant.description,
    ) {
        TitleRow(
            title = plant.title,
            titleStyle = MaterialTheme.typography.titleLarge.copy(color = Color.White),
            subTitleStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            subTitle = plant.description,
            verticalAlignment = Alignment.Bottom
        ) {
            AnimatedButtonIcon(icon = Icons.Shared, tint = Color.White, size = 24.dp)
        }
    }
}
