package com.ujizin.leafy.components.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ujizin.leafy.components.extensions.Content
import com.ujizin.leafy.components.ui.animated.AnimatedButtonIcon
import com.ujizin.leafy.components.ui.image.Icons

@Composable
internal fun Toolbar(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable Content?,
    trailingIcon: @Composable Content?,
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        leadingIcon?.invoke()
        trailingIcon?.invoke()
    }
}

@Preview
@Composable
private fun PreviewToolbar() {
    Toolbar(
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = { AnimatedButtonIcon(icon = Icons.Hamburger) },
        trailingIcon = { AnimatedButtonIcon(icon = Icons.Magnifier) },
    )
}
