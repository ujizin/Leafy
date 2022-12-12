package br.com.devlucasyuji.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import br.com.devlucasyuji.navigation.AnimatedEnterTransition
import br.com.devlucasyuji.navigation.AnimatedExitTransition
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.searchGraph(
    enterTransition: AnimatedEnterTransition,
    exitTransition: AnimatedExitTransition
) {
    composable(
        destination = Destination.Search,
        enterTransition = enterTransition,
        exitTransition = exitTransition
    ) { SearchSection() }
}
