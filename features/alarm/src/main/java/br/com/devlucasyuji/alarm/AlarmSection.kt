package br.com.devlucasyuji.alarm

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.devlucasyuji.components.ui.Section
import br.com.devlucasyuji.components.extensions.section

@Composable
fun AlarmSection() {
    Box(Modifier.fillMaxSize()) {
        Section(
            modifier = Modifier.section(),
            title = "Alarm"
        )
    }
}
