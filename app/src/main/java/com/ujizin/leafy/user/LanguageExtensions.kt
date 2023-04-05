package com.ujizin.leafy.user

import com.ujizin.leafy.domain.model.Language
import java.util.Locale

/**
 * Get locale by language.
 */
val Language.tag: String
    get() = when (this) {
        Language.PT -> "pt-BR"
        Language.EN -> "en-US"
    }
