package br.com.devlucasyuji.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import br.com.devlucasyuji.components.Section
import br.com.devlucasyuji.components.extensions.section

@Composable
fun NavController.SettingsSection() {
    Section(
        modifier = Modifier.section(),
        title = "Settings"
    )
}
