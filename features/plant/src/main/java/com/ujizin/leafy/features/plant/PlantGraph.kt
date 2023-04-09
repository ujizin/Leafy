package com.ujizin.leafy.features.plant

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import com.ujizin.leafy.core.navigation.AnimatedEnterTransition
import com.ujizin.leafy.core.navigation.AnimatedExitTransition
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.navigation.composable
import com.ujizin.leafy.core.ui.extensions.OnClick

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.plantGraph(
    onBackPressed: OnClick,
    enterTransition: AnimatedEnterTransition = { fadeIn() },
    exitTransition: AnimatedExitTransition = { fadeOut() }
) {
    composable(
        destination = Destination.PlantDetails,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
    ) {
        PlantDetails(
            onBackPressed = onBackPressed
        )
    }
}
