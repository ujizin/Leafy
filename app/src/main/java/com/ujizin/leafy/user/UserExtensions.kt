package com.ujizin.leafy.user

import android.content.Context
import android.os.Build
import com.ujizin.leafy.domain.model.Language
import java.util.Locale

fun Language.setLanguage(context: Context) = with(context) {
    val config = resources.configuration
    Locale.setDefault(locale)
    config.setLocale(locale)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        createConfigurationContext(config)
    }

    resources.updateConfiguration(config, resources.displayMetrics)
}
