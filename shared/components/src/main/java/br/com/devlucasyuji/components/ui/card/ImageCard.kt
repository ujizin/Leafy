package br.com.devlucasyuji.components.atomic.organisms.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.animation.Animate.Animated
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.atomic.molecules.BoxImage
import br.com.devlucasyuji.components.ui.label.TitleRow
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    data: Any?,
    contentDescription: String?,
    size: CardSize = CardSize.None,
    cardModifier: Modifier = Modifier,
    title: Text,
    subTitle: Text = Text(),
    animation: Animation = Animation.SlideToTop,
    vararg buttonIcons: ButtonIcon?,
) {
    val painter = rememberAsyncImagePainter(data)
    val content = movableContentOf {
        BoxImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(size.height)
                .then(modifier)
                .clip(RoundedCornerShape(4.dp)),
            painter = painter,
            boxModifier = cardModifier,
            contentDescription = contentDescription,
            contentAlignment = Alignment.BottomStart
        ) {
            TitleRow(title = title, subTitle = subTitle, verticalAlignment = Alignment.Bottom) {
                buttonIcons.forEach { it?.ButtonIcon() }
            }
        }
    }

    when (painter.state) {
        is AsyncImagePainter.State.Success -> animation.Animated { content() }
        else -> Box(Modifier.alpha(0F)) { content() }
    }
}
