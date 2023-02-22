package br.com.devlucasyuji.alarm

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import br.com.devlucasyuji.navigation.AnimatedEnterTransition
import br.com.devlucasyuji.navigation.AnimatedExitTransition
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.alarmGraph(
    onBackPressed: () -> Unit,
    onSaved: () -> Unit,
    enterTransition: AnimatedEnterTransition = { fadeIn() },
    exitTransition: AnimatedExitTransition = { fadeOut() }
) {
    composable(
        destination = Destination.Alarm,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
    ) {
        AlarmSection(onBackPressed = onBackPressed, onSaved = onSaved)
    }
}
