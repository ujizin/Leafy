package com.ujizin.leafy.about

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.ujizin.leafy.components.extensions.OnClick
import com.ujizin.leafy.navigation.Destination
import com.ujizin.leafy.navigation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.aboutGraph(
    onBackPressed: OnClick,
) {
    composable(
        destination = Destination.About
    ) {
        About(onBackPressed = onBackPressed)
    }
}