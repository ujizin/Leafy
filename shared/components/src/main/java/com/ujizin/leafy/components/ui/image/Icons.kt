package com.ujizin.leafy.components.ui.image

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ujizin.leafy.shared.components.R

enum class Icons(
    @DrawableRes internal val resId: Int,
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
    Close(R.drawable.close, R.string.close),
    Gallery(R.drawable.gallery, R.string.gallery),
    Refresh(R.drawable.refresh, R.string.refresh),
    Back(R.drawable.back, R.string.back);
}