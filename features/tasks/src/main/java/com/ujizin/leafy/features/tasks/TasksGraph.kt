package com.ujizin.leafy.features.tasks

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ujizin.leafy.core.navigation.AnimatedEnterTransition
import com.ujizin.leafy.core.navigation.AnimatedExitTransition
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.ui.extensions.OnClick

fun NavGraphBuilder.tasksGraph(
    enterTransition: AnimatedEnterTransition,
    exitTransition: AnimatedExitTransition,
    onPlantClick: (Long) -> Unit,
    onTakePictureClick: OnClick,
    onDrawerClick: OnClick,
) {
    composable<Destination.Tasks>(
        enterTransition = enterTransition,
        exitTransition = exitTransition,
    ) {
        TasksRoute(
            onPlantClick = onPlantClick,
            onTakePictureClick = onTakePictureClick,
            onDrawerClick = onDrawerClick,
        )
    }
}
