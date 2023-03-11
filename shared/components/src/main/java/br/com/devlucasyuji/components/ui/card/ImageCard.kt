package br.com.devlucasyuji.components.ui.card

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import br.com.devlucasyuji.components.extensions.Content
import br.com.devlucasyuji.components.ui.animated.animation.Animate.Animated
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.components.ui.image.BoxImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun BoxImage(
    modifier: Modifier = Modifier,
    data: Any?,
    contentDescription: String?,
    animation: Animation = Animation.SlideToTop,
    innerContent: @Composable Content = {}
) {
    val model = ImageRequest.Builder(LocalContext.current)
        .data(data)
        .size(Size.ORIGINAL)
        .crossfade(true)
        .build()

    val painter = rememberAsyncImagePainter(model = model)

    when (painter.state) {
        is AsyncImagePainter.State.Success -> Animated(animation = animation) {
            BoxImage(
                modifier = modifier,
                painter = painter,
                contentDescription = contentDescription,
                contentAlignment = Alignment.BottomStart
            ) { innerContent() }
        }

        else -> Box(Modifier.alpha(0F)) {
            BoxImage(
                modifier = modifier,
                painter = painter,
                contentDescription = contentDescription,
                contentAlignment = Alignment.BottomStart
            ) { innerContent() }
        }
    }
}
