package br.com.devlucasyuji.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.composable

fun NavGraphBuilder.searchGraph(navController: NavController) {
    composable(Destination.Search) { navController.SearchSection() }
}