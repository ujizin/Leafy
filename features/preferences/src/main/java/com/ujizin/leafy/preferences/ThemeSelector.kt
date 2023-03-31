package com.ujizin.leafy.preferences

import androidx.compose.runtime.Composable
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
    onThemeChanged: (Theme) -> Unit
) {
    Selector(
        modifier = modifier,
        title = stringResource(R.string.app_theme).capitalize(),
        subTitle = theme.toString(),
    ) {
        ModalSelector(
            title = stringResource(R.string.app_theme).capitalize(),
            currentValue = theme.toString(),
            values = Theme.values().map(Theme::toString),
            onValueChanged = { onThemeChanged(Theme.valueOf(it)) },
        )
    }
}
