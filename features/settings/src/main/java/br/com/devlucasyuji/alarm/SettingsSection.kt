package br.com.devlucasyuji.alarm

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import br.com.devlucasyuji.components.atomic.organisms.header

@Composable
fun NavController.SettingsSection() {
    LazyColumn {
        header(title = "Settings")
    }
}