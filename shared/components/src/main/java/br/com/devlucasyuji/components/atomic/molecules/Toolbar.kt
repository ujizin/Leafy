package br.com.devlucasyuji.components.atomic.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.props.Icons
import br.com.devlucasyuji.components.atomic.atoms.ButtonIcon

@Composable
internal fun Toolbar(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    leadingIcon: ButtonIcon?,
    trailingIcon: ButtonIcon?,
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(paddingValues),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        leadingIcon?.ButtonIcon()
        trailingIcon?.ButtonIcon()
    }
}

@Preview
@Composable
private fun PreviewToolbar() {
    Toolbar(
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = ButtonIcon(Icons.Hamburger),
        trailingIcon = ButtonIcon(Icons.Magnifier),
        paddingValues = PaddingValues(horizontal = 20.dp)
    )
}