package br.com.devlucasyuji.components.ui.navigation.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.R
import br.com.devlucasyuji.components.extensions.capitalize
import br.com.devlucasyuji.components.ui.header.HeaderTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DrawerContent(
    modifier: Modifier = Modifier, drawerItem: DrawerItem?, onDrawerClicked: (DrawerItem) -> Unit
) {
    ModalDrawerSheet(modifier) {
        Column(Modifier.padding(horizontal = 12.dp, vertical = 32.dp)) {
            HeaderTitle(
                title = "Lucas", // TODO name from data
                subTitle = stringResource(R.string.edit_name).capitalize()
            )
            Divider(Modifier.padding(vertical = 16.dp))
            DrawerItems(
                drawerItems = remember { DrawerItem.values().toList() },
                drawerItem = drawerItem,
                onDrawerClicked = onDrawerClicked
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerItems(
    drawerItems: List<DrawerItem>,
    drawerItem: DrawerItem?,
    onDrawerClicked: (DrawerItem) -> Unit,
) {
    drawerItems.forEach { item ->
        NavigationDrawerItem(
            modifier = Modifier.padding(vertical = 4.dp),
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