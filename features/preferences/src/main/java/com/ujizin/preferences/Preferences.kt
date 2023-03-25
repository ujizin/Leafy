package com.ujizin.preferences

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.extensions.capitalize
import br.com.devlucasyuji.components.extensions.paddingScreen
import br.com.devlucasyuji.components.local.LocalUser
import br.com.devlucasyuji.components.ui.Section
import br.com.devlucasyuji.components.ui.animated.AnimatedButtonIcon
import br.com.devlucasyuji.components.ui.image.Icons
import br.com.devlucasyuji.components.ui.selector.ModalSelector
import br.com.devlucasyuji.components.ui.selector.Selector
import br.com.devlucasyuji.domain.model.Theme
import br.com.devlucasyuji.domain.model.User
import br.com.devlucasyuji.preferences.R

@Composable
internal fun Preferences(
    onBackPressed: OnClick,
    viewModel: PreferencesViewModel = hiltViewModel(),
) {
    Section(
        modifier = Modifier.fillMaxSize(),
        title = stringResource(R.string.preferences_title),
        trailingIcon = {
            AnimatedButtonIcon(icon = Icons.Back, onClick = onBackPressed)
        },
    ) {
        Spacer(Modifier.height(16.dp))
        PreferencesContent(
            user = LocalUser.current,
            onUserChanged = viewModel::update,
        )
    }
}

@Composable
internal fun PreferencesContent(
    modifier: Modifier = Modifier,
    user: User,
    onUserChanged: (User) -> Unit,
) {
    Column(modifier) {
        Selector(
            modifier = Modifier
                .fillMaxWidth()
                .paddingScreen(vertical = 16.dp),
            title = stringResource(R.string.name).capitalize(),
            subTitle = user.nickname
        ) {

        }
        Selector(
            modifier = Modifier
                .fillMaxWidth()
                .paddingScreen(vertical = 16.dp),
            title = stringResource(R.string.language),
            subTitle = user.settings.language
        ) {

        }
        Selector(
            modifier = Modifier
                .fillMaxWidth()
                .paddingScreen(vertical = 16.dp),
            title = stringResource(R.string.app_theme).capitalize(),
            subTitle = user.settings.theme.toString()
        ) {
            ModalSelector(
                title = stringResource(R.string.app_theme).capitalize(),
                currentValue = user.settings.theme.toString(),
                values = Theme.values().map(Theme::toString),
                onValueChanged = {
                    onUserChanged(
                        user.copy(
                            settings = user.settings.copy(theme = Theme.valueOf(it))
                        )
                    )
                }
            )
        }
    }
}