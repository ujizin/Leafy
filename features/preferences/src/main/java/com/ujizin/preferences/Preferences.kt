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
import br.com.devlucasyuji.components.extensions.paddingScreen
import br.com.devlucasyuji.components.local.LocalUser
import br.com.devlucasyuji.components.ui.Section
import br.com.devlucasyuji.components.ui.animated.AnimatedButtonIcon
import br.com.devlucasyuji.components.ui.image.Icons
import br.com.devlucasyuji.domain.model.Language
import br.com.devlucasyuji.domain.model.Theme
import br.com.devlucasyuji.domain.model.User
import br.com.devlucasyuji.domain.model.update
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
        val user = LocalUser.current

        Spacer(Modifier.height(16.dp))
        PreferencesContent(
            user = user,
            onNicknameChanged = { nickname -> viewModel.update(user.update(nickname = nickname)) },
            onThemeChanged = { theme -> viewModel.update(user.update(theme = theme)) },
            onLanguageChanged = { language -> viewModel.update(user.update(language = language)) },
        )
    }
}

@Composable
internal fun PreferencesContent(
    modifier: Modifier = Modifier,
    user: User,
    onNicknameChanged: (String) -> Unit,
    onThemeChanged: (Theme) -> Unit,
    onLanguageChanged: (Language) -> Unit,
) {
    Column(modifier) {
        UserSelector(
            modifier = Modifier
                .fillMaxWidth()
                .paddingScreen(vertical = 16.dp),
            nickname = user.nickname,
            onNicknameChanged = onNicknameChanged
        )
        LanguageSelector(
            modifier = Modifier
                .fillMaxWidth()
                .paddingScreen(vertical = 16.dp),
            language = user.settings.language,
            onLanguageChanged = onLanguageChanged
        )
        ThemeSelector(
            modifier = Modifier
                .fillMaxWidth()
                .paddingScreen(vertical = 16.dp),
            theme = user.settings.theme,
            onThemeChanged = onThemeChanged
        )
    }
}
