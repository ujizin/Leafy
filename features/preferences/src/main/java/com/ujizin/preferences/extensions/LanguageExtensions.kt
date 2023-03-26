package com.ujizin.preferences.extensions

import androidx.annotation.StringRes
import br.com.devlucasyuji.domain.model.Language
import br.com.devlucasyuji.preferences.R

val Language.displayResId: Int
    @StringRes
    get() = when (this) {
        Language.PT -> R.string.portuguese
        Language.EN -> R.string.english
    }