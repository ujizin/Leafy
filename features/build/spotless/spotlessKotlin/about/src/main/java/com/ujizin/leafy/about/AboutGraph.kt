package com.ujizin.leafy.about

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.navigation.composable
import com.ujizin.leafy.core.ui.extensions.OnClick

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.aboutGraph(
    onBackPressed: OnClick,
) {
    composable(
        destination = Destination.About,
    ) {
        About(onBackPressed = onBackPressed)
    }
}
