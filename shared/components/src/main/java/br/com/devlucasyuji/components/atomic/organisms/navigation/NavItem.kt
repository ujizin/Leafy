package br.com.devlucasyuji.components.atomic.organisms.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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