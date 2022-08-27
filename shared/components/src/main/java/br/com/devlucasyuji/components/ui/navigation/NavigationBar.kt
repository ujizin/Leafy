package br.com.devlucasyuji.components.ui.navigation

import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import br.com.devlucasyuji.components.ui.animated.animation.Animate.Animated
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.components.ui.button.CameraButton
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.navigate

@Composable
internal fun NavigationBar(navController: NavController) {
    val navItemState by navController.currentNavItemAsState()
    val navItem = navItemState ?: return
    Animation.SlideToTop.copy(delayMillis = Animation.SmallDelay).Animated {
        androidx.compose.material3.NavigationBar {
            NavItem.values().forEach { item ->
                NavBarItem(selectedItem = navItem, item = item) {
                    navController.navigateToItem(item)
                }
            }
        }
        CameraButton(Modifier.offset(y = (-32).dp)) {
            navController.navigateToItem(navItem)
        }
    }
}

@Composable
private fun NavController.currentNavItemAsState(): State<NavItem?> {
    val selectedItem = remember { mutableStateOf<NavItem?>(NavItem.Home) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            val navItem = Destination.findByName(destination.route)?.navigationItem?.toNavItem()
            selectedItem.value = navItem
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedItem
}

private fun NavController.navigateToItem(item: NavItem) {
    navigate(item.destination) {
        launchSingleTop = false
        restoreState = true

        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
    }
}
