package com.ujizin.leafy.alarm.alarms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ujizin.leafy.components.ui.Section

@Composable
fun AlarmSection() {
    Box(Modifier.fillMaxSize()) {
        Section(title = "Alarm")
    }
}
