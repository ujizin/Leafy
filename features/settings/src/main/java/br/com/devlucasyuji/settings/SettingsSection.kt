package br.com.devlucasyuji.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.devlucasyuji.components.Section
import br.com.devlucasyuji.components.extensions.section

@Composable
fun SettingsSection() {
    Section(
        modifier = Modifier.section(),
        title = "Settings"
    )
}
