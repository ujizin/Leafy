package br.com.devlucasyuji.camerareminder

import br.com.devlucasyuji.domain.model.Language
import java.util.Locale

/**
 * Get locale by language.
 */
val Language.locale: Locale
    get() = when (this) {
        Language.PT -> Locale("pt", "BR")
        Language.EN -> Locale.ENGLISH
    }
