package com.ujizin.leafy.features.tasks

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.ujizin.leafy.core.navigation.AnimatedEnterTransition
import com.ujizin.leafy.core.navigation.AnimatedExitTransition
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.navigation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.tasksGraph(
    enterTransition: AnimatedEnterTransition,
    exitTransition: AnimatedExitTransition,
) {
    composable(
        destination = Destination.Tasks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
    ) {
        TasksRoute()
    }
}
