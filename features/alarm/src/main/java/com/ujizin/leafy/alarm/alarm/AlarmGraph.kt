package com.ujizin.leafy.alarm.alarm

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import com.ujizin.leafy.navigation.AnimatedEnterTransition
import com.ujizin.leafy.navigation.AnimatedExitTransition
import com.ujizin.leafy.navigation.Destination
import com.ujizin.leafy.navigation.composable

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