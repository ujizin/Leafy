package com.ujizin.leafy.core.ui.components.image

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.ujizin.leafy.core.components.R

@Immutable
enum class Icons(
    @DrawableRes internal val idRes: Int,
    @StringRes internal val descriptionRes: Int,
) {
    Hamburger(R.drawable.hamburger, R.string.hamburger),
    Magnifier(R.drawable.magnifier, R.string.magnifier),
    Home(R.drawable.home, R.string.home),
    Info(R.drawable.info, R.string.info),
    Alarm(R.drawable.alarm, R.string.alarm),
    Star(R.drawable.star, R.string.review_us),
    Settings(R.drawable.settings, R.string.settings),
    Add(R.drawable.add, R.string.add),
    Shared(R.drawable.shared, R.string.shared),
    Folder(R.drawable.folder, R.string.folder),
    Leaf(R.drawable.leaf, R.string.leaf),
    Others(R.drawable.others, R.string.others),
    Dropdown(R.drawable.dropdown, R.string.menu),
    Close(R.drawable.close, R.string.close),
    Gallery(R.drawable.gallery, R.string.gallery),
    Refresh(R.drawable.refresh, R.string.refresh),
    Back(R.drawable.back, R.string.back),
}
