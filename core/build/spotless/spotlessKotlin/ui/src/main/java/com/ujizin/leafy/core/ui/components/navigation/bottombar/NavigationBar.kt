package com.ujizin.leafy.core.ui.components.navigation.bottombar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.navigation.navigate
import com.ujizin.leafy.core.ui.components.animated.animation.Animate.Animated
import com.ujizin.leafy.core.ui.components.animated.animation.Animation
import com.ujizin.leafy.core.ui.components.button.CameraButton
import com.ujizin.leafy.core.ui.components.navigation.currentNavItemAsState
import com.ujizin.leafy.core.ui.state.keyboardAsState

@Composable
internal fun NavigationBar(
    navController: NavController,
    showBottomNavigation: Boolean,
) {
    val navItem by navController.currentNavItemAsState<BottomNavItem>()
    val isKeyboardOpen by keyboardAsState()
    val isBottomNavItem = remember(navItem) {
        BottomNavItem.values().any { bottomNavItem ->
            bottomNavItem.destination != Destination.Camera &&
                bottomNavItem.destination == navItem?.destination
        }
    }

    BottomNavigationBar(
        bottomNavItem = navItem,
        showBottomNavigation = isBottomNavItem && !isKeyboardOpen && showBottomNavigation,
        bottomNavItems = remember { BottomNavItem.values().toList() },
        onNavItemClicked = { destination ->
            navController.navigateToItem(destination)
        },
    )
}

@Composable
private fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    bottomNavItem: BottomNavItem?,
    showBottomNavigation: Boolean,
    bottomNavItems: List<BottomNavItem>,
    onNavItemClicked: (BottomNavItem) -> Unit,
) {
    Box(modifier = modifier) {
        Animated(
            visibleTarget = showBottomNavigation,
            animation = Animation.SlideToTop.copy(
                durationMillis = Animation.SmallDuration,
            ),
        ) {
            androidx.compose.material3.NavigationBar {
                bottomNavItems.forEach { item ->
                    NavBarItem(selectedItem = bottomNavItem, item = item, onClick = {
                        onNavItemClicked(item)
                    })
                }
            }

            CameraButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-32).dp),
            ) {
                onNavItemClicked(BottomNavItem.Camera)
            }
        }
    }
}

private fun NavController.navigateToItem(item: BottomNavItem) {
    if (currentBackStackEntry?.destination?.route == item.destination.route) return

    navigate(item.destination) {
        launchSingleTop = false
        restoreState = true
    }
}
