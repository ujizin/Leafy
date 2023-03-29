package com.ujizin.leafy.components.ui.animated

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ujizin.leafy.components.ui.animated.animation.Animate.Animated
import com.ujizin.leafy.components.ui.animated.animation.Animation
import com.ujizin.leafy.components.ui.image.Icons

@Composable
fun AnimatedIcon(
    modifier: Modifier = Modifier,
    animation: Animation = Animation.None,
    tint: Color = Color.Unspecified,
    icon: Icons
) {
    Animated(animation = animation) {
        Icon(
            modifier = modifier,
            painter = painterResource(icon.resId),
            tint = tint,
            contentDescription = stringResource(icon.descriptionRes)
        )
    }
}