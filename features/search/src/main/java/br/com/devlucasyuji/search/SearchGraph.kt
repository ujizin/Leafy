package br.com.devlucasyuji.search

import androidx.navigation.NavGraphBuilder
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.composable

fun NavGraphBuilder.searchGraph() {
    composable(Destination.Search) { SearchSection() }
}
