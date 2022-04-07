package br.com.devlucasyuji.components.atomic.atoms

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.Icons
import br.com.devlucasyuji.components.animation.Animate.Animated
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.extensions.OnClick

@Composable
internal fun Icon(
    icon: Icons,
    modifier: Modifier = Modifier,
    animation: Animation = Animation.None,
    onClick: OnClick
) {
    animation.Animated {
        Image(
            modifier = modifier
                .size(32.dp)
                .clickable(onClick = onClick),
            painter = painterResource(icon.resId),
            contentDescription = stringResource(icon.descriptionRes),
        )
    }
}
