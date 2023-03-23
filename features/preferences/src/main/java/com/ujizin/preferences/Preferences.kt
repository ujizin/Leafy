package com.ujizin.preferences

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.ui.Section
import br.com.devlucasyuji.components.ui.animated.AnimatedButtonIcon
import br.com.devlucasyuji.components.ui.image.Icons
import br.com.devlucasyuji.preferences.R

@Composable
internal fun Preferences(
    onBackPressed: OnClick,
    viewModel: PreferencesViewModel = hiltViewModel(),
) {
    Section(
        title = stringResource(R.string.preferences_title),
        trailingIcon = {
            AnimatedButtonIcon(icon = Icons.Back, onClick = onBackPressed)
        },
    ) {

    }
}
