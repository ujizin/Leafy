package com.ujizin.leafy.publish

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.navigation.AnimatedEnterTransition
import com.ujizin.leafy.core.navigation.AnimatedExitTransition
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.navigation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.publishGraph(
    onBackPressed: OnClick,
    onNextClick: OnClick,
    enterTransition: AnimatedEnterTransition = { fadeIn() },
    exitTransition: AnimatedExitTransition = { fadeOut() }
) {
    composable(
        destination = Destination.Publish,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
    ) {
        PublishSection(
            onBackPressed = onBackPressed,
            onFinishPublish = onNextClick
        )
    }
}
