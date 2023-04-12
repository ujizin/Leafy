package com.ujizin.leafy.preferences

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.navigation.composable
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.preferences.ui.Preferences

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.preferencesGraph(
    onBackPressed: OnClick,
) {
    composable(
        destination = Destination.Preferences,
    ) {
        Preferences(onBackPressed = onBackPressed)
    }
}
