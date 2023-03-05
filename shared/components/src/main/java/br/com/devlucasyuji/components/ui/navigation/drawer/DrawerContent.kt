package br.com.devlucasyuji.components.ui.navigation.drawer

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.extensions.capitalize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DrawerContent(
    modifier: Modifier = Modifier,
    drawerItem: DrawerItem?,
    onDrawerClicked: (DrawerItem) -> Unit
) {
    ModalDrawerSheet(modifier) {
        DrawerItem.values().forEach { item ->
            NavigationDrawerItem(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                icon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = item.iconRes),
                        contentDescription = null
                    )
                },
                label = { Text(text = stringResource(id = item.labelRes).capitalize()) },
                selected = drawerItem?.destination == item.destination,
                onClick = { onDrawerClicked(item) },
            )
        }

    }
}