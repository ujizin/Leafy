package br.com.devlucasyuji.domain.model

import java.util.Locale

enum class Language {
    PT, EN;

    companion object {

        val systemLanguage: Language
            get() = when (Locale.getDefault().language) {
                "pt" -> PT
                else -> EN
            }
    }
}
