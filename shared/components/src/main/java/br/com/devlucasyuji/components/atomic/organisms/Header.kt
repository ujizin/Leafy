package br.com.devlucasyuji.components.atomic.organisms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.atomic.molecules.HeaderTitle
import br.com.devlucasyuji.components.atomic.molecules.Toolbar
import br.com.devlucasyuji.components.extensions.Empty

fun LazyListScope.header(
    title: String,
    subTitle: String = String.Empty,
    modifier: Modifier = Modifier,
    toolbarPadding: PaddingValues = PaddingValues(horizontal = 20.dp),
    headerTitlePadding: PaddingValues = PaddingValues(top = 24.dp, start = 20.dp, end = 20.dp)
) {
    item {
        Column(modifier) {
            Toolbar(paddingValues = toolbarPadding)
            HeaderTitle(
                title = title,
                subTitle = subTitle,
                modifier = Modifier,
                paddingValues = headerTitlePadding
            )
        }
    }
}