package com.ujizin.leafy.preferences

import androidx.navigation.NavGraphBuilder
import com.ujizin.leafy.core.navigation.AnimatedEnterTransition
import com.ujizin.leafy.core.navigation.AnimatedExitTransition
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.navigation.composable
import com.ujizin.leafy.preferences.ui.PreferencesRoute

fun NavGraphBuilder.preferencesGraph(
    enterTransition: AnimatedEnterTransition,
    exitTransition: AnimatedExitTransition,
) {
    composable(
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        destination = Destination.Preferences,
    ) {
        PreferencesRoute()
    }
}
