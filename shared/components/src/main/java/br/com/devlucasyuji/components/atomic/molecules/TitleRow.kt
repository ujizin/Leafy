package br.com.devlucasyuji.components.atomic.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.animation.Animate.Animated
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.extensions.Content
import br.com.devlucasyuji.components.props.Text

@Composable
fun TitleRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.HorizontalOrVertical = Arrangement.SpaceBetween,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    title: Text,
    subTitle: Text = Text(),
    animation: Animation = Animation.SlideToTop,
    iconSpacedBy: Dp = 8.dp,
    icons: @Composable Content
) {
    animation.Animated {
        Row(
            modifier = modifier,
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment,
        ) {
            HeaderTitle(
                modifier = Modifier.weight(1F),
                title = title,
                subTitle = subTitle,
                paddingValues = PaddingValues(0.dp),
                animation = Animation.None
            )
            Row(horizontalArrangement = Arrangement.spacedBy(iconSpacedBy)) {
                icons()
            }
        }
    }
}
