package com.ujizin.leafy.preferences

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ujizin.leafy.core.navigation.AnimatedEnterTransition
import com.ujizin.leafy.core.navigation.AnimatedExitTransition
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.preferences.ui.PreferencesRoute

fun NavGraphBuilder.preferencesGraph(
    enterTransition: AnimatedEnterTransition,
    exitTransition: AnimatedExitTransition,
) {
    composable<Destination.Preferences>(
        enterTransition = enterTransition,
        exitTransition = exitTransition,
    ) {
        PreferencesRoute()
    }
}
