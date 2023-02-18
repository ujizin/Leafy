package br.com.devlucasyuji.publish

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.navigation.AnimatedEnterTransition
import br.com.devlucasyuji.navigation.AnimatedExitTransition
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.composable

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
            onNextClick = onNextClick
        )
    }
}
