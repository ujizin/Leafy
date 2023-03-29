package com.ujizin.leafy.preferences

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.ujizin.leafy.components.extensions.OnClick
import com.ujizin.leafy.navigation.Destination
import com.ujizin.leafy.navigation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.preferencesGraph(
    onBackPressed: OnClick,
) {
    composable(
        destination = Destination.Preferences
    ) {
        Preferences(onBackPressed = onBackPressed)
    }
}