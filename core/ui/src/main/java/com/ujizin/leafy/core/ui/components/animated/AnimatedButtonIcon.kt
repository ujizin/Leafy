package com.ujizin.leafy.core.ui.components.animated

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.takeOrElse
import com.ujizin.leafy.core.ui.components.animated.animation.Animate.Animated
import com.ujizin.leafy.core.ui.components.animated.animation.Animation
import com.ujizin.leafy.core.ui.components.image.Icons
import com.ujizin.leafy.core.ui.extensions.OnClick

@Composable
fun AnimatedButtonIcon(
    modifier: Modifier = Modifier,
    icon: Icons,
    animation: Animation = Animation.None,
    size: Dp = 24.dp,
    tint: Color? = null,
    background: Color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5F),
    innerPaddingValues: PaddingValues = PaddingValues(12.dp),
    shape: Shape = RoundedCornerShape(8.dp),
    onClick: OnClick = {},
) {
    Animated(animation = animation) {
        Box(
            modifier = modifier
                .clip(shape)
                .clickable(onClick = onClick)
                .background(background, shape)
                .padding(innerPaddingValues),
        ) {
            androidx.compose.material3.Icon(
                modifier = Modifier.size(size.takeOrElse { 32.dp }),
                painter = painterResource(icon.idRes),
                tint = tint ?: MaterialTheme.colorScheme.onBackground,
                contentDescription = stringResource(icon.descriptionRes),
            )
        }
    }
}

@Preview("Hamburger")
@Composable
private fun PreviewHamburgerIcon() {
    AnimatedButtonIcon(icon = Icons.Hamburger) {}
}

@Preview("Magnifier")
@Composable
private fun PreviewMagnifierIcon() {
    AnimatedButtonIcon(icon = Icons.Magnifier) {}
}
