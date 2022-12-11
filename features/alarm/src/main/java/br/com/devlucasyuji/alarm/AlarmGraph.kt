package br.com.devlucasyuji.alarm

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.alarmGraph() {
    composable(Destination.Alarm) { AlarmSection() }
}
