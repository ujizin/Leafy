package com.ujizin.about

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.composable

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