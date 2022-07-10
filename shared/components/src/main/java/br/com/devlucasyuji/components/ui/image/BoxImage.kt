package br.com.devlucasyuji.components.atomic.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import br.com.devlucasyuji.components.extensions.Content

@Composable
fun BoxImage(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String?,
    contentAlignment: Alignment = Alignment.TopStart,
    boxModifier: Modifier = Modifier,
    content: @Composable Content
) {
    Box(modifier = modifier) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentDescription = contentDescription,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(boxModifier),
            contentAlignment = contentAlignment,
        ) { content() }
    }
}
