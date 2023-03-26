package com.ujizin.preferences

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import br.com.devlucasyuji.components.ui.selector.ModalSelector
import br.com.devlucasyuji.components.ui.selector.Selector
import br.com.devlucasyuji.domain.model.Language
import br.com.devlucasyuji.preferences.R
import com.ujizin.preferences.extensions.displayResId
import com.ujizin.preferences.model.getLanguages

@Composable
fun LanguageSelector(
    modifier: Modifier = Modifier,
    language: Language,
    onLanguageChanged: (Language) -> Unit,
) {
    var showModal by remember { mutableStateOf(false) }
    Selector(
        modifier = modifier,
        title = stringResource(R.string.language),
        showModal = showModal,
        onModalStateChanged = { showModal = it },
        subTitle = stringResource(language.displayResId)
    ) {
        val context = LocalContext.current
        val languages = remember(language) { getLanguages(context) }
        val currentLanguage = remember(language) {
            context.getString(language.displayResId)
        }
        ModalSelector(
            title = stringResource(R.string.language),
            currentValue = currentLanguage,
            values = remember(language) { languages.map { it.displayName } },
            onValueChanged = { displayName ->
                val lang = languages.first { it.displayName == displayName }.language
                onLanguageChanged(lang)
                showModal = false
            }
        )
    }
}