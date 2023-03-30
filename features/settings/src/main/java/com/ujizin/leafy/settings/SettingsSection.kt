package com.ujizin.leafy.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ujizin.leafy.core.ui.components.Section

@Composable
fun SettingsSection() {
    Box(Modifier.fillMaxSize()) {
        Section(title = "Settings")
    }
}
