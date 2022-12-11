package br.com.devlucasyuji.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.searchGraph() {
    composable(Destination.Search) { SearchSection() }
}
