package br.com.devlucasyuji.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.atomic.atoms.ButtonIcon
import br.com.devlucasyuji.components.atomic.organisms.Header
import br.com.devlucasyuji.components.atomic.organisms.Section
import br.com.devlucasyuji.components.atomic.organisms.card.CardSize
import br.com.devlucasyuji.components.atomic.organisms.card.ImageCard
import br.com.devlucasyuji.components.extensions.capitalize
import br.com.devlucasyuji.components.extensions.innerShadow
import br.com.devlucasyuji.components.props.Icons
import br.com.devlucasyuji.components.props.Shadow
import br.com.devlucasyuji.components.props.Text
import br.com.devlucasyuji.domain.model.Photo
import java.io.File

@Composable
fun NavController.HomeRoute(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.homeState.collectAsState()
    when (val result: UIState = state) {
        UIState.Loading -> TODO()
        is UIState.Success -> HomeScreen(result)
        is UIState.Error -> TODO()
    }
}

@Composable
private fun HomeScreen(result: UIState.Success) {
    Section(
        header = Header(
            title = Text(
                stringResource(
                    id = R.string.hello_user,
                    result.nickname.capitalize()
                ).capitalize()
            ),
            subTitle = Text(stringResource(id = R.string.welcome_back).capitalize()),
            leadingIcon = ButtonIcon(Icons.Hamburger, Animation.SlideToEnd),
            trailingIcon = ButtonIcon(Icons.Magnifier, Animation.SlideToStart)
        ),
    ) {
        when {
            result.photos.isEmpty() -> item { EmptyPhotoCard() }
            else -> items(result.photos, key = { it.id }) { HomePhotoCard(it) }
        }
    }
}

@Composable
private fun EmptyPhotoCard() {

}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun LazyItemScope.HomePhotoCard(photo: Photo) {
    ImageCard(
        modifier = Modifier
            .animateItemPlacement()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        cardModifier = Modifier
            .innerShadow(Shadow.Black, RoundedCornerShape(4.dp))
            .padding(12.dp),
        data = File(photo.filePath),
        contentDescription = photo.description,
        title = Text(photo.title, TextStyle(color = Color.White)),
        subTitle = Text(photo.description, TextStyle(color = Color.White)),
        size = CardSize.Large,
        buttonIcons = arrayOf(
            ButtonIcon(Icons.Shared, tint = Color.White, size = 24.dp),
        )
    )
}
