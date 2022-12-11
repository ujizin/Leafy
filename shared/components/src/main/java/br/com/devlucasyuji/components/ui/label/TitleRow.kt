package br.com.devlucasyuji.components.ui.label

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.extensions.Content
import br.com.devlucasyuji.components.ui.animated.animation.Animate.Animated
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.components.ui.header.HeaderTitle

@Composable
fun TitleRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.HorizontalOrVertical = Arrangement.SpaceBetween,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    title: String,
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge,
    subTitle: String,
    subTitleStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    animation: Animation = Animation.SlideToTop,
    iconSpacedBy: Dp = 8.dp,
    icons: @Composable Content
) {
    Animated(animation = animation) {
        Row(
            modifier = modifier,
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment,
        ) {
            HeaderTitle(
                modifier = Modifier.weight(1F),
                title = title,
                subTitle = subTitle,
                animation = Animation.None,
                titleStyle = titleStyle,
                subTitleStyle = subTitleStyle
            )
            Row(horizontalArrangement = Arrangement.spacedBy(iconSpacedBy)) { icons() }
        }
    }
}
