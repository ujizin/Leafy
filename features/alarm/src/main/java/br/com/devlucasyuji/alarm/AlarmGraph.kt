package br.com.devlucasyuji.alarm

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.composable

fun NavGraphBuilder.alarmGraph(navController: NavController) {
    composable(Destination.Alarm) { navController.AlarmSection() }
}