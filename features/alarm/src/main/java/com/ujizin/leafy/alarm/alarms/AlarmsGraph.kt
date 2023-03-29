package com.ujizin.leafy.alarm.alarms

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.ujizin.leafy.navigation.AnimatedEnterTransition
import com.ujizin.leafy.navigation.AnimatedExitTransition
import com.ujizin.leafy.navigation.Destination
import com.ujizin.leafy.navigation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.alarmsGraph(
    enterTransition: AnimatedEnterTransition,
    exitTransition: AnimatedExitTransition
) {
    composable(
        destination = Destination.Alarms,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
    ) {
        AlarmSection()
    }
}