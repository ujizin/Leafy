package com.ujizin.leafy.alarm.ui

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ujizin.leafy.core.navigation.AnimatedEnterTransition
import com.ujizin.leafy.core.navigation.AnimatedExitTransition
import com.ujizin.leafy.core.navigation.Destination

fun NavGraphBuilder.alarmGraph(
    onBackPressed: () -> Unit,
    onSaved: () -> Unit,
    enterTransition: AnimatedEnterTransition = { fadeIn() },
    exitTransition: AnimatedExitTransition = { fadeOut() },
) {
    composable<Destination.Alarm>(
        enterTransition = enterTransition,
        exitTransition = exitTransition,
    ) {
        AlarmRoute(onBackPressed = onBackPressed, onSaved = onSaved)
    }
}
