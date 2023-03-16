package br.com.devlucasyuji.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.navigation.AnimatedEnterTransition
import br.com.devlucasyuji.navigation.AnimatedExitTransition
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.composable
import br.com.devlucasyuji.search.ui.SearchSection

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.searchGraph(
    enterTransition: AnimatedEnterTransition,
    exitTransition: AnimatedExitTransition,
    onDrawerClick: OnClick,
    onTakePictureClick: OnClick,
    onScroll: (Boolean) -> Unit
) {
    composable(
        destination = Destination.Search,
        enterTransition = enterTransition,
        exitTransition = exitTransition
    ) {
        SearchSection(
            onDrawerClick = onDrawerClick,
            onScroll = onScroll,
            onTakePictureClick = onTakePictureClick,
        ) }
}
