package com.ujizin.leafy.core.ui.components.navigation.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ujizin.leafy.core.components.R
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.ui.components.navigation.NavItem

internal enum class BottomNavItem(
    @DrawableRes val iconRes: Int? = null,
    @StringRes val labelRes: Int? = null,
    override val destination: Destination,
) : NavItem {
    Home(R.drawable.home, R.string.home, Destination.Home),
    Search(R.drawable.magnifier, R.string.search, Destination.Search),
    Camera(destination = Destination.Camera),
    Alarm(R.drawable.alarm, R.string.tasks, Destination.Tasks),
    Others(R.drawable.others, R.string.others, Destination.Preferences),
    ;

    companion object {
        internal var previousRoute: String? = null
    }
}
