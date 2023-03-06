package br.com.devlucasyuji.components.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import br.com.devlucasyuji.components.ui.navigation.bottombar.NavigationBar
import br.com.devlucasyuji.components.ui.navigation.currentNavItemAsState
import br.com.devlucasyuji.components.ui.navigation.drawer.DrawerContent
import br.com.devlucasyuji.components.ui.navigation.drawer.DrawerItem
import br.com.devlucasyuji.navigation.navigate
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scaffold(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    navController: NavController,
    content: @Composable BoxScope.() -> Unit
) {
    val currentDrawerItem by navController.currentNavItemAsState<DrawerItem>()
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = currentDrawerItem != null,
        drawerContent = {
            val scope = rememberCoroutineScope()
            DrawerContent(
                drawerItem = currentDrawerItem,
                onDrawerClicked = { drawerItem ->
                    scope.launch { drawerState.close() }
                    navController.navigate(drawerItem.destination)
                },
            )
        },
    ) {
        androidx.compose.material3.Scaffold(
            modifier = modifier,
            bottomBar = { NavigationBar(navController) },
        ) { innerPadding ->
            Box(
                Modifier.padding(innerPadding),
                content = content
            )
        }
    }
}
