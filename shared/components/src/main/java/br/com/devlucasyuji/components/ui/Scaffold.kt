package br.com.devlucasyuji.components.ui

import android.annotation.SuppressLint
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
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.navigate
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scaffold(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    navController: NavController,
    showBottomNavigation: Boolean,
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
                onUserClick = {
                    scope.launch { drawerState.close() }
                    navController.navigate(Destination.Preferences)
                },
                onDrawerClicked = { drawerItem ->
                    scope.launch { drawerState.close() }
                    navController.navigate(drawerItem.destination)
                },
                onCloseDrawer = {
                    scope.launch { drawerState.close() }
                }
            )
        },
    ) {
        androidx.compose.material3.Scaffold(
            modifier = modifier,
            bottomBar = { NavigationBar(navController, showBottomNavigation) },
        ) { innerPadding ->
            Box(
                modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                content = content
            )
        }
    }
}
