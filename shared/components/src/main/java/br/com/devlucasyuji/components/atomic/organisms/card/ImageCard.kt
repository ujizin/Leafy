package br.com.devlucasyuji.components.atomic.organisms.card

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.animation.Animate.Animated
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.atomic.atoms.ButtonIcon
import br.com.devlucasyuji.components.atomic.molecules.BoxImage
import br.com.devlucasyuji.components.atomic.molecules.TitleRow
import br.com.devlucasyuji.components.props.Text

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
    animation.Animated {
        BoxImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(size.height)
                .then(modifier)
                .clip(RoundedCornerShape(4.dp))
            ,
            data = data,
            boxModifier = cardModifier,
            contentDescription = contentDescription,
            contentAlignment = Alignment.BottomStart
        ) {
            TitleRow(title = title, subTitle = subTitle) {
                buttonIcons.forEach { it?.ButtonIcon() }
            }
        }
    }
}