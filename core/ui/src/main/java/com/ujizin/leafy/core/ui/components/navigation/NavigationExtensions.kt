package com.ujizin.leafy.core.ui.components.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.ui.components.navigation.bottombar.BottomNavItem

private enum class NavDirection {
    Start, End, None,
}

private fun navDirection(
    navController: NavController,
    previousDestination: String? = BottomNavItem.previousRoute,
): NavDirection {
    previousDestination ?: return NavDirection.End

    val currentDestination = navController.currentBackStackEntry?.destination?.route

    val previousBottomNavItem = BottomNavItem.entries.firstOrNull {
        it.destination.route == previousDestination
    } ?: return NavDirection.None

    val currentBottomNavItem = BottomNavItem.entries.firstOrNull {
        it.destination.route == currentDestination
    } ?: return NavDirection.None

    if (currentBottomNavItem == BottomNavItem.Camera) return NavDirection.None

    return when {
        currentBottomNavItem.ordinal >= previousBottomNavItem.ordinal -> NavDirection.Start
        else -> NavDirection.End
    }
}

fun AnimatedContentTransitionScope<*>.navigationEnterTransition(
    navController: NavController,
): EnterTransition? {
    val direction = when (navDirection(navController)) {
        NavDirection.Start -> AnimatedContentTransitionScope.SlideDirection.Start
        NavDirection.End -> AnimatedContentTransitionScope.SlideDirection.End
        NavDirection.None -> null
    } ?: return null

    return slideIntoContainer(
        direction,
        animationSpec = spring(
            Spring.DampingRatioLowBouncy,
            Spring.StiffnessMediumLow,
        ),
    )
}

fun AnimatedContentTransitionScope<*>.navigationExitTransition(
    navController: NavController,
): ExitTransition? {
    val direction = when (navDirection(navController)) {
        NavDirection.Start -> AnimatedContentTransitionScope.SlideDirection.Start
        NavDirection.End -> AnimatedContentTransitionScope.SlideDirection.End
        NavDirection.None -> null
    } ?: return null

    return slideOutOfContainer(
        direction,
        animationSpec = spring(
            Spring.DampingRatioNoBouncy,
            Spring.StiffnessMedium,
        ),
    )
}

@Composable
internal inline fun <reified T : NavItem> NavController.currentNavItemAsState(
    initialNavItem: T? = null,
): State<T?> {
    val selectedItem = remember { mutableStateOf(initialNavItem) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            val currentDestination = Destination.findByName(destination.route)
            val value = T::class.java.enumConstants?.firstOrNull {
                it.destination == currentDestination
            }
            selectedItem.value = value
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedItem
}
