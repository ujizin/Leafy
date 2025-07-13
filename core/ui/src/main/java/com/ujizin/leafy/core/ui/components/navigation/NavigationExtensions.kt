package com.ujizin.leafy.core.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.ujizin.leafy.core.navigation.qualifiedRoute

@Composable
internal inline fun <reified T : NavItem> NavController.currentNavItemAsState(
    initialNavItem: T? = null,
): State<T?> {
    val selectedItem = remember(initialNavItem) { mutableStateOf(initialNavItem) }
    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            val currentDestination = destination.qualifiedRoute
            val value = T::class.java.enumConstants?.firstOrNull {
                it.destination::class.qualifiedName == currentDestination
            }
            selectedItem.value = value
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedItem
}
