package br.com.devlucasyuji.components.atomic.molecules

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.Icons
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.atomic.atoms.Icon
import br.com.devlucasyuji.components.extensions.OnClick

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(horizontal = 20.dp),
    leadingIcon: Icons = Icons.Hamburger,
    trailingIcon: Icons = Icons.Magnifier,
    leadingAnimation: Animation = Animation.End,
    trailingAnimation: Animation = Animation.Start,
    onLeadingIconClick: OnClick = {},
    onTrailingIconClick: OnClick = {},
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(paddingValues),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Icon(icon = leadingIcon, animation = leadingAnimation, onClick = onLeadingIconClick)
        Icon(icon = trailingIcon, animation = trailingAnimation, onClick = onTrailingIconClick)
    }
}

@Preview
@Composable
private fun PreviewToolbar() {
    Toolbar(
        modifier = Modifier.fillMaxWidth(),
        leadingAnimation = Animation.None,
        trailingAnimation = Animation.None
    )
}