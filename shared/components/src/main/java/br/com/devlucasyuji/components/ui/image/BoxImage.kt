package br.com.devlucasyuji.components.ui.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.extensions.Content
import br.com.devlucasyuji.components.extensions.innerShadow
import br.com.devlucasyuji.components.props.Shadow

@Composable
fun BoxImage(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String?,
    contentAlignment: Alignment = Alignment.TopStart,
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
                .innerShadow(Shadow.Black, RoundedCornerShape(4.dp))
                .padding(12.dp),
            contentAlignment = contentAlignment,
        ) { content() }
    }
}
