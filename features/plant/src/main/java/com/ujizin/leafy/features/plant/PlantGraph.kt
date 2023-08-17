package com.ujizin.leafy.features.plant

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ujizin.leafy.core.navigation.AnimatedEnterTransition
import com.ujizin.leafy.core.navigation.AnimatedExitTransition
import com.ujizin.leafy.core.navigation.Args
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.navigation.composable
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.ui.extensions.fullSlideInHorizontally
import com.ujizin.leafy.core.ui.extensions.fullSlideOutHorizontally
import com.ujizin.leafy.features.plant.detail.ui.PlantDetailsRoute
import com.ujizin.leafy.features.plant.edit.ui.PlantEditRoute

fun NavGraphBuilder.plantGraph(
    onBackPressed: OnClick,
    enterTransition: AnimatedEnterTransition = { fullSlideInHorizontally() },
    exitTransition: AnimatedExitTransition = { fullSlideOutHorizontally() },
) {
    composable(
        destination = Destination.PlantDetails,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        arguments = listOf(
            navArgument(Args.PlantId) { type = NavType.LongType },
        ),
    ) {
        PlantDetailsRoute(
            onBackPressed = onBackPressed,
        )
    }
    composable(
        destination = Destination.PlantEdit,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        arguments = listOf(
            navArgument(Args.PlantId) { type = NavType.LongType },
        ),
    ) {
        PlantEditRoute(
            onBackPressed = onBackPressed,
        )
    }
}
