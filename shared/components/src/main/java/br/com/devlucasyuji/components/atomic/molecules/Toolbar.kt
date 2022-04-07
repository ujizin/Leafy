package br.com.devlucasyuji.components.atomic.molecules

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.Icons
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.atomic.atoms.Flex
import br.com.devlucasyuji.components.atomic.atoms.Icon

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(horizontal = 20.dp),
    leadingIcon: Icons = Icons.Hamburger,
    trailingIcon: Icons = Icons.Magnifier,
) {
    Row(modifier.padding(paddingValues)) {
        Icon(icon = leadingIcon, animation = Animation.End) {}
        Flex()
        Icon(icon = trailingIcon, animation = Animation.Start) {}
    }
}