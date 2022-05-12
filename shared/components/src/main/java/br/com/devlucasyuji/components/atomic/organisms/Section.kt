package br.com.devlucasyuji.components.atomic.organisms

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.atomic.atoms.ButtonIcon
import br.com.devlucasyuji.components.props.Text

data class Header(
    val modifier: Modifier = Modifier,
    val title: Text,
    val subTitle: Text = Text(),
    val trailingIcon: ButtonIcon? = null,
    val leadingIcon: ButtonIcon? = null,
    val headerAnimation: Animation = Animation.SlideToTop,
    val toolbarPadding: PaddingValues = PaddingValues(horizontal = 20.dp),
    val headerTitlePadding: PaddingValues? = null,
    internal val isHidden: Boolean = false,
) {

    companion object {
        val None = Header(title = Text(), isHidden = true)
    }
}

@Composable
fun Section(
    header: Header,
    spacedBy: Dp = 16.dp,
    body: LazyListScope.() -> Unit,
) {
    LazyColumn {
        with(header) {
            if (!isHidden) {
                header(
                    modifier = modifier,
                    title = title,
                    subTitle = subTitle,
                    trailingIcon = trailingIcon,
                    leadingIcon = leadingIcon,
                    headerAnimation = headerAnimation,
                    toolbarPadding = toolbarPadding,
                    headerTitlePadding = headerTitlePadding
                )
            }
        }
        item { Spacer(Modifier.height(spacedBy)) }
        body()
        item { Spacer(Modifier.height(32.dp)) }
    }
}