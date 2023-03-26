package com.ujizin.preferences

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.devlucasyuji.components.extensions.capitalize
import br.com.devlucasyuji.components.ui.selector.ModalSelector
import br.com.devlucasyuji.components.ui.selector.Selector
import br.com.devlucasyuji.domain.model.Theme
import br.com.devlucasyuji.preferences.R

@Composable
fun ThemeSelector(
    modifier: Modifier = Modifier,
    theme: Theme,
    onThemeChanged: (Theme) -> Unit,
) {
    Selector(
        modifier = modifier,
        title = stringResource(R.string.app_theme).capitalize(),
        subTitle = theme.toString()
    ) {
        ModalSelector(
            title = stringResource(R.string.app_theme).capitalize(),
            currentValue = theme.toString(),
            values = Theme.values().map(Theme::toString),
            onValueChanged = { onThemeChanged(Theme.valueOf(it)) }
        )
    }
}