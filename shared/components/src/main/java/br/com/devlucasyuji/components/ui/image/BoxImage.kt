package br.com.devlucasyuji.components.ui.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
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
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp)
    ) {
        var imageHeight by remember { mutableStateOf(0.dp) }
        Image(
            modifier = with(LocalDensity.current) {
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .onGloballyPositioned {
                        imageHeight = it.size.height.toDp()
                    }
            },
            painter = painter,
            contentScale = ContentScale.Fit,
            contentDescription = contentDescription,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
                .innerShadow(Shadow.Black, RoundedCornerShape(4.dp))
                .padding(12.dp),
            contentAlignment = contentAlignment,
        ) { content() }
    }
}
