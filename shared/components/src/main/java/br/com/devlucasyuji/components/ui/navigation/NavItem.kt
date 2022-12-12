package br.com.devlucasyuji.components.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessMediumLow
import androidx.compose.animation.core.spring
import androidx.navigation.NavController
import br.com.devlucasyuji.components.R
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.NavigationItem

internal enum class NavItem(
    @DrawableRes val iconRes: Int? = null,
    @StringRes val labelRes: Int? = null,
    val destination: Destination,
) {
    Home(R.drawable.home, R.string.home, Destination.Home),
    Search(R.drawable.magnifier, R.string.search, Destination.Search),
    Camera(destination = Destination.Camera),
    Alarm(R.drawable.alarm, R.string.alarm, Destination.Alarm),
    Others(R.drawable.others, R.string.others, Destination.Others)
}

internal fun NavigationItem.toNavItem(): NavItem? = when (this) {
    NavigationItem.Home -> NavItem.Home
    NavigationItem.Search -> NavItem.Search
    NavigationItem.Camera -> NavItem.Camera
    NavigationItem.Alarm -> NavItem.Alarm
    NavigationItem.Others -> NavItem.Others
    else -> null
}

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
            DampingRatioLowBouncy,
            StiffnessMediumLow
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
            DampingRatioLowBouncy,
            StiffnessMediumLow
        )
    )
}