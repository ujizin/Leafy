package br.com.devlucasyuji.components.atomic.organisms

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.R
import br.com.devlucasyuji.components.animation.Animate.Animated
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.atomic.molecules.CameraButton
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.extensions.capitalize


enum class NavItem(@DrawableRes val iconRes: Int? = null, @StringRes val labelRes: Int? = null) {
    Home(R.drawable.home, R.string.home),
    Search(R.drawable.magnifier, R.string.search),
    Camera,
    Alarm(R.drawable.alarm, R.string.alarm),
    Others(R.drawable.others, R.string.others)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scaffold(modifier: Modifier = Modifier, content: LazyListScope.() -> Unit) {
    var selectedItem by remember { mutableStateOf(NavItem.Home) }

    androidx.compose.material3.Scaffold(modifier = modifier, bottomBar = {
        Animation.SlideToTop.copy(delayMillis = Animation.SmallDelay).Animated {
            NavigationBar {
                NavItem.values().forEach { item ->
                    NavBarItem(selectedItem, item) {
                        selectedItem = item
                    }
                }
            }
            CameraButton {}
        }
    }) { innerPadding ->
        LazyColumn(
            Modifier
                .padding(vertical = 32.dp)
                .padding(innerPadding),
            content = content
        )
    }
}

@Composable
internal fun RowScope.NavBarItem(selectedItem: NavItem, item: NavItem, onClick: OnClick) {
    // TODO add navigation with destinations
    val labelRes = item.labelRes ?: return Box(Modifier.size(64.dp))
    val iconRes = item.iconRes ?: return

    NavigationBarItem(
        icon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(iconRes),
                contentDescription = null
            )
        },
        label = {
            Text(
                text = stringResource(labelRes).capitalize(),
                Modifier.animateContentSize()
            )
        },
        alwaysShowLabel = item == selectedItem,
        selected = selectedItem == item,
        onClick = onClick,
    )
}