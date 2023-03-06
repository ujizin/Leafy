package br.com.devlucasyuji.components.ui.navigation.drawer

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.devlucasyuji.components.R
import br.com.devlucasyuji.components.ui.navigation.NavItem
import br.com.devlucasyuji.navigation.Destination

enum class DrawerItem(
    @DrawableRes val iconRes: Int,
    @StringRes val labelRes: Int,
    override val destination: Destination,
) : NavItem {
    Home(R.drawable.home, R.string.home, Destination.Home),
    Search(R.drawable.magnifier, R.string.search, Destination.Search),
    Alarms(R.drawable.alarm, R.string.alarm, Destination.Alarms),
    ReviewUs(R.drawable.star, R.string.review_us, Destination.Review),
    About(R.drawable.info, R.string.about, Destination.About),
    Settings(R.drawable.others, R.string.settings, Destination.Others),
}
