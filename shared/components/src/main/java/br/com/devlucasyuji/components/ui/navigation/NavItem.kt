package br.com.devlucasyuji.components.ui.navigation

import br.com.devlucasyuji.components.ui.navigation.bottombar.BottomNavItem
import br.com.devlucasyuji.components.ui.navigation.drawer.DrawerItem
import br.com.devlucasyuji.navigation.Destination

/**
 * NavItem is used for components that needs to be navigated to one destination.
 *
 * Used in [BottomNavItem] & [DrawerItem].
 *
 * */
interface NavItem {
    /**
     * Item destination.
     * */
    val destination: Destination
}