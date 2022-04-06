package br.com.devlucasyuji.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationGraph(startDestination: Destination = Destination.Home) {

    val navController = rememberNavController()

    NavHost(navController, startDestination.route) {
        ModulesNavGraph.forEach { ModuleNavGraph -> ModuleNavGraph() }
    }
}
