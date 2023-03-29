package com.ujizin.leafy.components.ui.navigation

import com.ujizin.leafy.components.ui.navigation.bottombar.BottomNavItem
import com.ujizin.leafy.components.ui.navigation.drawer.DrawerItem
import com.ujizin.leafy.navigation.Destination

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