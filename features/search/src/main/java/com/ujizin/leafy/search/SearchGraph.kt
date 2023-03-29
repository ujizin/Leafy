package com.ujizin.leafy.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ujizin.leafy.components.extensions.OnClick
import com.ujizin.leafy.navigation.AnimatedEnterTransition
import com.ujizin.leafy.navigation.AnimatedExitTransition
import com.ujizin.leafy.navigation.Args.SearchAutoFocus
import com.ujizin.leafy.navigation.Destination
import com.ujizin.leafy.navigation.composable
import com.ujizin.leafy.search.ui.SearchSection

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.searchGraph(
    enterTransition: AnimatedEnterTransition,
    exitTransition: AnimatedExitTransition,
    onDrawerClick: OnClick,
    onTakePictureClick: OnClick,
    onScroll: (Boolean) -> Unit
) {
    composable(
        destination = Destination.Search,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        arguments = listOf(
            navArgument(SearchAutoFocus) {
                type = NavType.BoolType
                defaultValue = false
            }
        )
    ) {
        SearchSection(
            onDrawerClick = onDrawerClick,
            onScroll = onScroll,
            onTakePictureClick = onTakePictureClick,
        )
    }
}
