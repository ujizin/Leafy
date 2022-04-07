package br.com.devlucasyuji.components.atomic.molecules

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.devlucasyuji.components.Icons
import br.com.devlucasyuji.components.atomic.atoms.Flex
import br.com.devlucasyuji.components.atomic.atoms.Icon

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    leadingIcon: Icons = Icons.Hamburger,
    trailingIcon: Icons = Icons.Magnifier,
) {
    Row(modifier) {
        Icon(icon = leadingIcon) {}
        Flex()
        Icon(icon = trailingIcon) {}
    }
}