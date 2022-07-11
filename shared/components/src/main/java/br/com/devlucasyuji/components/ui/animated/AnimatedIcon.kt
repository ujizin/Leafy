package br.com.devlucasyuji.components.ui.animated

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.ui.animated.animation.Animate.Animated
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.ui.image.Icons

@Composable
fun AnimatedIcon(
    modifier: Modifier = Modifier,
    icon: Icons,
    animation: Animation = Animation.None,
    size: Dp = Dp.Unspecified,
    tint: Color? = null,
    onClick: OnClick = {}
) {
    animation.Animated {
        IconButton(
            onClick = onClick,
            modifier = modifier
                .size(32.dp)
                .clickable(onClick = onClick),
        ) {
            androidx.compose.material3.Icon(
                modifier = Modifier.size(size),
                painter = painterResource(icon.resId),
                tint = tint ?: MaterialTheme.colorScheme.onPrimary,
                contentDescription = stringResource(icon.descriptionRes),
            )
        }
    }
}

@Preview("Hamburger")
@Composable
private fun PreviewHamburgerIcon() {
    AnimatedIcon(icon = Icons.Hamburger) {}
}

@Preview("Magnifier")
@Composable
private fun PreviewMagnifierIcon() {
    AnimatedIcon(icon = Icons.Magnifier) {}
}
