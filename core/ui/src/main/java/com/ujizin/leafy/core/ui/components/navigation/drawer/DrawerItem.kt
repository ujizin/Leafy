package com.ujizin.leafy.core.ui.components.navigation.drawer

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ujizin.leafy.core.components.R
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.ui.components.navigation.NavItem

enum class DrawerItem(
    @DrawableRes val iconRes: Int,
    @StringRes val labelRes: Int,
    override val destination: Destination,
) : NavItem {
    Home(R.drawable.home, R.string.home, Destination.Home),
    Search(R.drawable.magnifier, R.string.search, Destination.Search),
    Alarms(R.drawable.alarm, R.string.tasks, Destination.Tasks),
    ReviewUs(R.drawable.star, R.string.review_us, Destination.Review),
    About(R.drawable.info, R.string.about, Destination.About),
    Settings(R.drawable.others, R.string.settings, Destination.Others),
}
