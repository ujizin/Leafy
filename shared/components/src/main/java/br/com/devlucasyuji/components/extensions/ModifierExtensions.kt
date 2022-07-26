package br.com.devlucasyuji.components.extensions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

fun Modifier.innerShadow(colors: List<Color>, shape: Shape = RectangleShape) = then(
    Modifier.background(
        brush = Brush.verticalGradient(colors),
        shape = shape
    )
)

fun Modifier.section() = then(Modifier.padding(top = 32.dp, start = 20.dp, end = 20.dp))