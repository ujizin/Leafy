package com.ujizin.leafy.home

import androidx.navigation.NavGraphBuilder
import com.ujizin.leafy.core.navigation.AnimatedEnterTransition
import com.ujizin.leafy.core.navigation.AnimatedExitTransition
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.navigation.composable
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.home.ui.HomeRoute

fun NavGraphBuilder.homeGraph(
    enterTransition: AnimatedEnterTransition,
    exitTransition: AnimatedExitTransition,
    onTakePictureClick: OnClick,
    onSearchClick: OnClick,
    onDrawerClick: OnClick,
    onPlantClick: (Long) -> Unit,
) {
    composable(
        destination = Destination.Home,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
    ) {
        HomeRoute(
            onTakePictureClick = onTakePictureClick,
            onDrawerClick = onDrawerClick,
            onSearchClick = onSearchClick,
            onPlantClick = onPlantClick,
        )
    }
}
