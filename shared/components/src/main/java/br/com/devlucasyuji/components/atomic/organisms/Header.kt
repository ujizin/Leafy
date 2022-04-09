package br.com.devlucasyuji.components.atomic.organisms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
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
import br.com.devlucasyuji.themes.CameraReminderTheme

fun LazyListScope.header(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String = String.Empty,
    trailingIcon: ButtonIcon? = null,
    leadingIcon: ButtonIcon? = null,
    headerAnimation: Animation = Animation.SlideToTop,
    toolbarPadding: PaddingValues = PaddingValues(horizontal = 20.dp),
    headerTitlePadding: PaddingValues? = null,
) {
    item {
        Column(modifier) {
            Toolbar(
                modifier = Modifier.padding(top = 32.dp),
                paddingValues = toolbarPadding,
                trailingIcon = trailingIcon,
                leadingIcon = leadingIcon
            )
            HeaderTitle(
                title = title,
                subTitle = subTitle,
                modifier = Modifier,
                animation = headerAnimation,
                paddingValues = when {
                    headerTitlePadding != null -> headerTitlePadding
                    trailingIcon != null && leadingIcon != null -> PaddingValues(
                        top = 24.dp,
                        start = 20.dp,
                        end = 20.dp
                    )
                    else -> PaddingValues(horizontal = 20.dp)
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewHeader() {
    CameraReminderTheme {
        LazyColumn {
            header(
                modifier = Modifier.padding(top = 32.dp),
                title = "Hello Lucas",
                subTitle = "Welcome back!",
                leadingIcon = ButtonIcon(Icons.Hamburger),
                trailingIcon = ButtonIcon(Icons.Magnifier),
                headerAnimation = Animation.None
            )
        }
    }
}