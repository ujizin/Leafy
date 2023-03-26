package com.ujizin.preferences.model

import android.content.Context
import br.com.devlucasyuji.domain.model.Language
import com.ujizin.preferences.extensions.displayResId

data class PreferenceLanguage(
    val language: Language,
    val displayName: String
)

fun getLanguages(context: Context) = Language.values().map {
    PreferenceLanguage(it, context.getString(it.displayResId))
}
