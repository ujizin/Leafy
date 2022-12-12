package br.com.devlucasyuji.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.navigation.AnimatedEnterTransition
import br.com.devlucasyuji.navigation.AnimatedExitTransition
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeGraph(
    enterTransition: AnimatedEnterTransition,
    exitTransition: AnimatedExitTransition,
    onTakePictureClick: OnClick,
) {
    composable(
        destination = Destination.Home,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
    ) {
        HomeRoute(onTakePictureClick = onTakePictureClick)
    }
}
