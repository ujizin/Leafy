package com.ujizin.leafy.components.ui.card

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import com.ujizin.leafy.components.extensions.Content
import com.ujizin.leafy.components.ui.animated.animation.Animate.Animated
import com.ujizin.leafy.components.ui.animated.animation.Animation
import com.ujizin.leafy.components.ui.image.BoxImage
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
    val context = LocalContext.current
    val model = remember(data) {
        ImageRequest.Builder(context)
            .data(data)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build()
    }
    val painter = rememberAsyncImagePainter(model = model)

    val alphaAnimated by animateFloatAsState(
        targetValue = if (painter.state is AsyncImagePainter.State.Success) 1F else 0F
    )
    Animated(
        modifier = Modifier.alpha(alphaAnimated),
        animation = animation
    ) {
        BoxImage(
            modifier = modifier,
            painter = painter,
            contentDescription = contentDescription,
            contentAlignment = Alignment.BottomStart
        ) { innerContent() }
    }
}
