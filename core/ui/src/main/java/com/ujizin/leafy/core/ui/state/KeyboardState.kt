package com.ujizin.leafy.core.ui.state

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity

@Composable
fun keyboardAsState(): State<Boolean> {
    val density = LocalDensity.current
    val ime = WindowInsets.ime
    return remember { derivedStateOf { ime.getBottom(density) > 0 } }
}
