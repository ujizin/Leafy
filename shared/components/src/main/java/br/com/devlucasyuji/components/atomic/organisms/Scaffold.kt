package br.com.devlucasyuji.components.atomic.organisms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import br.com.devlucasyuji.components.atomic.organisms.navigation.NavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavController.Scaffold(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    androidx.compose.material3.Scaffold(
        modifier = modifier,
        bottomBar = { NavigationBar() }
    ) { innerPadding ->
        Box(
            Modifier.padding(innerPadding),
            content = content
        )
    }
}
