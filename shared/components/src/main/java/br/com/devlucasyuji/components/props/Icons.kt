package br.com.devlucasyuji.components.props

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.devlucasyuji.components.R

enum class Icons(
    @DrawableRes internal val resId: Int,
    @StringRes internal val descriptionRes: Int,
) {
    Hamburger(R.drawable.hamburger, R.string.hamburger),
    Magnifier(R.drawable.magnifier, R.string.magnifier),
    Home(R.drawable.home, R.string.home),
    Info(R.drawable.info, R.string.info),
    Alarm(R.drawable.alarm, R.string.alarm),
    Star(R.drawable.star, R.string.star),
    Settings(R.drawable.settings, R.string.settings),
    Add(R.drawable.add, R.string.add),
    Shared(R.drawable.shared, R.string.shared),
    Others(R.drawable.others, R.string.others);
}
