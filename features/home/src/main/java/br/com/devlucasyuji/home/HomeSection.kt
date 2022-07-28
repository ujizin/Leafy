package br.com.devlucasyuji.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import androidx.navigation.NavController
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.components.ui.animated.AnimatedIcon
import br.com.devlucasyuji.components.ui.card.CardSize
import br.com.devlucasyuji.components.ui.card.BoxImage
import br.com.devlucasyuji.components.extensions.capitalize
import br.com.devlucasyuji.components.ui.image.Icons
import br.com.devlucasyuji.components.Section
import br.com.devlucasyuji.components.extensions.section
import br.com.devlucasyuji.components.ui.label.TitleRow
import br.com.devlucasyuji.domain.model.Photo
import java.io.File

@Composable
fun NavController.HomeRoute(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.homeState.collectAsState()
    when (val result: UIState = state) {
        UIState.Loading -> {}
        is UIState.Success -> HomeScreen(result)
        is UIState.Error -> {}
    }
}

@Composable
private fun HomeScreen(result: UIState.Success) {
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
//                result.photos.isEmpty() -> item { EmptyPhotoCard() }
            else -> items(20) {
                HomePhotoCard(
                    Photo(
                        0,
                        "yuji apenas",
                        "",
                        "",
                        "eu apenas",
                        false,
                        1
                    )
                )
            }
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
private fun EmptyPhotoCard() {

}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun LazyItemScope.HomePhotoCard(photo: Photo) {
    BoxImage(
        modifier = Modifier
            .animateItemPlacement()
            .height(CardSize.Large.height)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        data = "https://pps.whatsapp.net/v/t61.24694-24/179134053_520513506372508_3465206539243170804_n.jpg?ccb=11-4&oh=01_AVxbQe01QoC8ku5i0d0k1Kd76j0_dhk89ISzNOIoEL7bSw&oe=62F2ADD0",
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
