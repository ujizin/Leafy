package br.com.devlucasyuji.settings

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import br.com.devlucasyuji.components.atomic.organisms.header
import br.com.devlucasyuji.components.props.Text

@Composable
fun NavController.SettingsSection() {
    LazyColumn {
        header(title = Text("Settings"))
    }
}