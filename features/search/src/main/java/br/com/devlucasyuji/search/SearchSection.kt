package br.com.devlucasyuji.search

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import br.com.devlucasyuji.components.Icons
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.atomic.atoms.ButtonIcon
import br.com.devlucasyuji.components.atomic.organisms.header

@Composable
fun NavController.SearchSection() {
    LazyColumn {
        header(
            leadingIcon = ButtonIcon(
                icon = Icons.Hamburger,
                animation = Animation.SlideToTop,
            ),
            trailingIcon = ButtonIcon(
                icon = Icons.Settings,
                animation = Animation.SlideToTop
            ),
            title = "Search"
        )
    }
}