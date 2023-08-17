package com.ujizin.leafy.search

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ujizin.leafy.core.navigation.AnimatedEnterTransition
import com.ujizin.leafy.core.navigation.AnimatedExitTransition
import com.ujizin.leafy.core.navigation.Args.SearchAutoFocus
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.navigation.composable
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.search.ui.SearchRoute

fun NavGraphBuilder.searchGraph(
    enterTransition: AnimatedEnterTransition,
    exitTransition: AnimatedExitTransition,
    onDrawerClick: OnClick,
    onTakePictureClick: OnClick,
    onPlantClick: (Long) -> Unit,
) {
    composable(
        destination = Destination.Search,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        arguments = listOf(
            navArgument(SearchAutoFocus) {
                type = NavType.BoolType
                defaultValue = false
            },
        ),
    ) {
        SearchRoute(
            onDrawerClick = onDrawerClick,
            onTakePictureClick = onTakePictureClick,
            onPlantClick = onPlantClick
        )
    }
}
