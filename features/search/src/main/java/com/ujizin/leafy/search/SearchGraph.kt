package com.ujizin.leafy.search

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ujizin.leafy.core.navigation.AnimatedEnterTransition
import com.ujizin.leafy.core.navigation.AnimatedExitTransition
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.search.ui.SearchRoute

fun NavGraphBuilder.searchGraph(
    enterTransition: AnimatedEnterTransition,
    exitTransition: AnimatedExitTransition,
    onDrawerClick: OnClick,
    onTakePictureClick: OnClick,
    onPlantClick: (Long) -> Unit,
) {
    composable<Destination.Search>(
        enterTransition = enterTransition,
        exitTransition = exitTransition,
    ) {
        SearchRoute(
            onDrawerClick = onDrawerClick,
            onTakePictureClick = onTakePictureClick,
            onPlantClick = onPlantClick,
        )
    }
}
