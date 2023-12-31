package com.ujizin.leafy.core.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.compose.animation.AnimatedContentTransitionScope as TransitionScope

typealias AnimatedEnterTransition = (TransitionScope<NavBackStackEntry>.() -> EnterTransition?)

typealias AnimatedExitTransition = (TransitionScope<NavBackStackEntry>.() -> ExitTransition?)

fun NavGraphBuilder.composable(
    destination: Destination,
    enterTransition: AnimatedEnterTransition = { fadeIn() },
    exitTransition: AnimatedExitTransition = { fadeOut() },
    popEnterTransition: AnimatedEnterTransition? = enterTransition,
    popExitTransition: AnimatedExitTransition? = exitTransition,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit,
) {
    composable(
        route = destination.route,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        content = content,
    )
}

fun NavController.navigate(destination: Destination, builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(destination.route, builder)
}

fun NavController.navigateUp(times: Int) {
    repeat(times) { navigateUp() }
}
