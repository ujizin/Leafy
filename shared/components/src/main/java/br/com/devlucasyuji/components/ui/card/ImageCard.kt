package br.com.devlucasyuji.components.ui.card

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import br.com.devlucasyuji.components.ui.animated.animation.Animate.Animated
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.components.ui.image.BoxImage
import br.com.devlucasyuji.components.extensions.Content
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

@Composable
fun BoxImage(
    modifier: Modifier = Modifier,
    data: Any?,
    contentDescription: String?,
    animation: Animation = Animation.SlideToTop,
    innerContent: @Composable Content = {}
) {
    val painter = rememberAsyncImagePainter(data)
    val content = movableContentOf {
        BoxImage(
            modifier = modifier,
            painter = painter,
            contentDescription = contentDescription,
            contentAlignment = Alignment.BottomStart
        ) { innerContent() }
    }

    when (painter.state) {
        is AsyncImagePainter.State.Success -> Animated(animation = animation) { content() }
        else -> Box(Modifier.alpha(0F)) { content() }
    }
}
