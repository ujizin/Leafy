package br.com.devlucasyuji.alarm

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.devlucasyuji.components.Section
import br.com.devlucasyuji.components.extensions.section

@Composable
fun AlarmSection() {
    Section(
        modifier = Modifier.section(),
        title = "Alarm"
    )
}
