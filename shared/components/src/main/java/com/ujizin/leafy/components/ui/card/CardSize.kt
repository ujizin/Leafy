package com.ujizin.leafy.components.ui.card

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class CardSize(val height: Dp) {
    None(Dp.Unspecified),
    Small(150.dp),
    Medium(200.dp),
    Large(350.dp);
}
