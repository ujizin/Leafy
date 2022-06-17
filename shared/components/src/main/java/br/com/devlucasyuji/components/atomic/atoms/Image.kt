package br.com.devlucasyuji.components.atomic.atoms

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

@Composable
internal fun Image(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String?,
) {
    androidx.compose.foundation.Image(
        modifier = modifier,
        painter = painter,
        contentScale = contentScale,
        contentDescription = contentDescription
    )
}
