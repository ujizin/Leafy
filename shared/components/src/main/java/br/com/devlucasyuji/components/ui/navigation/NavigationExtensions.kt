package br.com.devlucasyuji.components.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import br.com.devlucasyuji.components.ui.navigation.bottombar.BottomNavItem
import br.com.devlucasyuji.navigation.Destination

private enum class NavDirection {
    Start, End, None
}

private fun navDirection(navController: NavController): NavDirection {
    val previousBackStackEntry = navController.previousBackStackEntry ?: return run {
        return@run NavDirection.End
    }

    val previousDestination = previousBackStackEntry.destination.route
    val currentDestination = navController.currentBackStackEntry?.destination?.route

    val previousBottomNavItem = BottomNavItem.values().firstOrNull {
        it.destination.route == previousDestination
    } ?: return NavDirection.None

    val currentBottomNavItem = BottomNavItem.values().firstOrNull {
        it.destination.route == currentDestination
    } ?: return NavDirection.None

    if (currentBottomNavItem == BottomNavItem.Camera) return NavDirection.None

    return when {
        currentBottomNavItem.ordinal > previousBottomNavItem.ordinal -> NavDirection.Start
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

@Composable
internal inline fun <reified T : NavItem> NavController.currentNavItemAsState(initialNavItem: T? = null): State<T?> {
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
