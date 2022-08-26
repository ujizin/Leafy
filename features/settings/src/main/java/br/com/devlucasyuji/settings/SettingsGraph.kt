package br.com.devlucasyuji.settings

import androidx.navigation.NavGraphBuilder
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.composable

fun NavGraphBuilder.settingsGraph() {
    composable(Destination.Others) { SettingsSection() }
}
