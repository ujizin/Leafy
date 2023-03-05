package br.com.devlucasyuji.components.ui.navigation.bottombar

import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.devlucasyuji.components.ui.animated.animation.Animate.Animated
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.components.ui.button.CameraButton
import br.com.devlucasyuji.components.ui.navigation.currentNavItemAsState
import br.com.devlucasyuji.navigation.navigate

@Composable
internal fun NavigationBar(navController: NavController) {
    val navItem by navController.currentNavItemAsState(BottomNavItem.Home)
    Animated(animation = Animation.SlideToTop.copy(delayMillis = Animation.SmallDelay)) {
        androidx.compose.material3.NavigationBar {
            BottomNavItem.values().forEach { item ->
                NavBarItem(selectedItem = navItem, item = item) {
                    navController.navigateToItem(item)
                }
            }
        }
        CameraButton(Modifier.offset(y = (-32).dp)) {
            navController.navigateToItem(BottomNavItem.Camera)
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
