package com.ujizin.leafy.alarm.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import com.ujizin.leafy.core.navigation.AnimatedEnterTransition
import com.ujizin.leafy.core.navigation.AnimatedExitTransition
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.navigation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.alarmGraph(
    onBackPressed: () -> Unit,
    onSaved: () -> Unit,
    enterTransition: AnimatedEnterTransition = { fadeIn() },
    exitTransition: AnimatedExitTransition = { fadeOut() },
) {
    composable(
        destination = Destination.Alarm,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
    ) {
        AlarmRoute(onBackPressed = onBackPressed, onSaved = onSaved)
    }
}
