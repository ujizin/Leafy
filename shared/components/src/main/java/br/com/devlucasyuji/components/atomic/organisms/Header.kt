package br.com.devlucasyuji.components.atomic.organisms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.Icons
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.atomic.atoms.ButtonIcon
import br.com.devlucasyuji.components.atomic.molecules.HeaderTitle
import br.com.devlucasyuji.components.atomic.molecules.Toolbar
import br.com.devlucasyuji.components.extensions.Empty

fun LazyListScope.header(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String = String.Empty,
    trailingIcon: ButtonIcon?,
    leadingIcon: ButtonIcon?,
    headerAnimation: Animation = Animation.SlideToTop,
    toolbarPadding: PaddingValues = PaddingValues(horizontal = 20.dp),
    headerTitlePadding: PaddingValues = PaddingValues(top = 24.dp, start = 20.dp, end = 20.dp)
) {
    item {
        Column(modifier) {
            Toolbar(
                paddingValues = toolbarPadding,
                trailingIcon = trailingIcon,
                leadingIcon = leadingIcon
            )
            HeaderTitle(
                title = title,
                subTitle = subTitle,
                modifier = Modifier,
                animation = headerAnimation,
                paddingValues = headerTitlePadding
            )
        }
    }
}

@Preview
@Composable
private fun PreviewHeader() {
    LazyColumn {
        header(
            title = "Hello Lucas",
            subTitle = "Welcome back!",
            leadingIcon = ButtonIcon(Icons.Hamburger),
            trailingIcon = ButtonIcon(Icons.Magnifier),
            headerAnimation = Animation.None
        )
    }
}