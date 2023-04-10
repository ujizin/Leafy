package com.ujizin.leafy.camera

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring.DampingRatioNoBouncy
import androidx.compose.animation.core.Spring.StiffnessLow
import androidx.compose.animation.core.Spring.StiffnessMediumLow
import androidx.compose.animation.core.spring
import androidx.navigation.NavGraphBuilder
import com.ujizin.leafy.camera.ui.CameraRoute
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.navigation.composable
import com.ujizin.leafy.core.ui.extensions.OnClick

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.cameraGraph(
    onBackPressed: OnClick,
    onSaveClicked: () -> Unit,
) {
    composable(
        Destination.Camera,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Up,
                animationSpec = spring(DampingRatioNoBouncy, StiffnessLow),
                initialOffset = { it * 2 },
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Down,
                animationSpec = spring(DampingRatioNoBouncy, StiffnessMediumLow),
                targetOffset = { -it },
            )
        },
    ) {
        CameraRoute(
            onCloseClicked = onBackPressed,
            onImageSaved = onSaveClicked,
        )
    }
}
