package br.com.devlucasyuji.components.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.navigation.NavController

private enum class NavDirection {
    Start, End, None
}

private fun navDirection(navController: NavController): NavDirection {
    val previousDestination = navController.previousBackStackEntry?.destination?.route
    val currentDestination = navController.currentBackStackEntry?.destination?.route

    val previousNavItem = NavItem.values().firstOrNull {
        it.destination.route == previousDestination
    } ?: return NavDirection.None

    val currentNavItem = NavItem.values().firstOrNull {
        it.destination.route == currentDestination
    } ?: return NavDirection.None

    if (currentNavItem == NavItem.Camera) return NavDirection.None

    return when {
        currentNavItem.ordinal > previousNavItem.ordinal -> NavDirection.Start
        else -> NavDirection.End
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<*>.navigationEnterTransition(
    navController: NavController
): EnterTransition? {
    val direction = when (navDirection(navController)) {
        NavDirection.Start -> AnimatedContentScope.SlideDirection.Start
        NavDirection.End -> AnimatedContentScope.SlideDirection.End
        NavDirection.None -> null
    } ?: return null

    return slideIntoContainer(
        direction,
        animationSpec = spring(
            Spring.DampingRatioLowBouncy,
            Spring.StiffnessMediumLow
        )
    )
}

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<*>.navigationExitTransition(
    navController: NavController
): ExitTransition? {
    val direction = when (navDirection(navController)) {
        NavDirection.Start -> AnimatedContentScope.SlideDirection.Start
        NavDirection.End -> AnimatedContentScope.SlideDirection.End
        NavDirection.None -> null
    } ?: return null

    return slideOutOfContainer(
        direction,
        animationSpec = spring(
            Spring.DampingRatioLowBouncy,
            Spring.StiffnessMediumLow
        )
    )
}