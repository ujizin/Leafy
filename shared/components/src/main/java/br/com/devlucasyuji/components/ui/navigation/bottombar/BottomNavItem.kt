package br.com.devlucasyuji.components.ui.navigation.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.devlucasyuji.components.R
import br.com.devlucasyuji.components.ui.navigation.NavItem
import br.com.devlucasyuji.navigation.Destination

internal enum class BottomNavItem(
    @DrawableRes val iconRes: Int? = null,
    @StringRes val labelRes: Int? = null,
    override val destination: Destination,
) : NavItem {
    Home(R.drawable.home, R.string.home, Destination.Home),
    Search(R.drawable.magnifier, R.string.search, Destination.Search),
    Camera(destination = Destination.Camera),
    Alarm(R.drawable.alarm, R.string.alarm, Destination.Alarms),
    Others(R.drawable.others, R.string.others, Destination.Others)
}
