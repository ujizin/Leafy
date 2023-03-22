package br.com.devlucasyuji.search.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.extensions.capitalize
import br.com.devlucasyuji.components.ui.EmptySection
import br.com.devlucasyuji.search.R
import br.com.devlucasyuji.components.R as CR

@Composable
internal fun SearchEmptyList(
    searchText: String,
    modifier: Modifier = Modifier,
    onTakePictureClick: OnClick,
) {
    val context = LocalContext.current
    val description = remember(searchText) {
        if (searchText.isEmpty()) {
            context.getString(CR.string.empty_plant)
        } else {
            context.getString(R.string.search_empty_description, searchText)
        }
    }.capitalize()

    EmptySection(
        modifier = modifier,
        description = description,
        onClick = onTakePictureClick,
    )
}
