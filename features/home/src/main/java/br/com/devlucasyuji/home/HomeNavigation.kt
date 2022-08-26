package br.com.devlucasyuji.home

import androidx.navigation.NavGraphBuilder
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.composable

fun NavGraphBuilder.homeGraph() {
    composable(Destination.Home) { HomeRoute() }
}
