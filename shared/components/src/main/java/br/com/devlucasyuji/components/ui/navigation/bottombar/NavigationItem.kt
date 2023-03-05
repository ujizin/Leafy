package br.com.devlucasyuji.components.ui.navigation.bottombar

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.extensions.capitalize

@Composable
internal fun RowScope.NavBarItem(
    modifier: Modifier = Modifier,
    selectedItem: BottomNavItem?,
    item: BottomNavItem,
    onClick: OnClick
) {
    val labelRes = item.labelRes ?: return Box(Modifier.size(64.dp))
    val iconRes = item.iconRes ?: return

    NavigationBarItem(
        modifier = modifier,
        icon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(iconRes),
                contentDescription = null
            )
        },
        label = {
            Text(
                modifier = Modifier.animateContentSize(),
                text = stringResource(labelRes).capitalize(),
                color = MaterialTheme.colorScheme.onSurface,
            )
        },
        alwaysShowLabel = item == selectedItem,
        selected = selectedItem == item,
        onClick = onClick,
    )
}
