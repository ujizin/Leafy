package br.com.devlucasyuji.components.atomic.organisms.card

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class CardSize(val height: Dp) {
    None(Dp.Unspecified),
    Small(80.dp),
    Medium(120.dp),
    Large(200.dp);
}
