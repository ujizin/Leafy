package com.ujizin.leafy.preferences.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ujizin.leafy.core.ui.components.selector.ModalSelector
import com.ujizin.leafy.core.ui.components.selector.Selector
import com.ujizin.leafy.core.ui.extensions.capitalize
import com.ujizin.leafy.domain.model.Theme
import com.ujizin.leafy.features.preferences.R

@Composable
fun ThemeSelector(
    modifier: Modifier = Modifier,
    theme: Theme,
    onThemeChanged: (Theme) -> Unit,
) {
    var showModal by remember { mutableStateOf(false) }
    Selector(
        modifier = modifier,
        title = stringResource(R.string.app_theme).capitalize(),
        subTitle = theme.toString(),
        showModal = showModal,
        onModalStateChanged = { showModal = it }
    ) {
        ModalSelector(
            title = stringResource(R.string.app_theme).capitalize(),
            currentValue = theme.toString(),
            values = Theme.values().map(Theme::toString),
            onValueChanged = { onThemeChanged(Theme.valueOf(it)) },
        )
    }
}
