package com.ujizin.leafy.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.ujizin.leafy.core.navigation.AnimatedEnterTransition
import com.ujizin.leafy.core.navigation.AnimatedExitTransition
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.navigation.composable
import com.ujizin.leafy.core.ui.extensions.OnClick

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeGraph(
    enterTransition: AnimatedEnterTransition,
    exitTransition: AnimatedExitTransition,
    onTakePictureClick: OnClick,
    onSearchClick: OnClick,
    onDrawerClick: OnClick
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
        )
    }
}
