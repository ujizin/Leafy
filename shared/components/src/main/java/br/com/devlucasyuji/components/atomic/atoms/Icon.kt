package br.com.devlucasyuji.components.atomic.atoms

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import br.com.devlucasyuji.components.Icons
import br.com.devlucasyuji.components.extensions.OnClick

@Composable
internal fun Icon(
    icon: Icons,
    modifier: Modifier = Modifier,
    onClick: OnClick
) {
    Image(
        modifier = modifier.clickable(onClick = onClick),
        painter = painterResource(icon.resId),
        contentDescription = stringResource(icon.descriptionRes),
    )
}