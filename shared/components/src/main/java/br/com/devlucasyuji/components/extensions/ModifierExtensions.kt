package br.com.devlucasyuji.components.extensions

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape

fun Modifier.innerShadow(colors: List<Color>, shape: Shape = RectangleShape) = then(
    Modifier.background(
        brush = Brush.verticalGradient(colors),
        shape = shape
    )
)