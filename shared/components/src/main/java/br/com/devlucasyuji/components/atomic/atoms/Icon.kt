package br.com.devlucasyuji.components.atomic.atoms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
        IconButton(
            onClick = onClick,
            modifier = modifier
                .size(32.dp)
                .clickable(onClick = onClick),
        ) {
            androidx.compose.material3.Icon(
                painter = painterResource(icon.resId),
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = stringResource(icon.descriptionRes),
            )
        }
    }
}

@Preview("Hamburger")
@Composable
private fun PreviewHamburgerIcon() {
    Icon(Icons.Hamburger) {}
}

@Preview("Magnifier")
@Composable
private fun PreviewMagnifierIcon() {
    Icon(Icons.Magnifier) {}
}