package br.com.devlucasyuji.search.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.extensions.capitalize
import br.com.devlucasyuji.components.ui.EmptySection
import br.com.devlucasyuji.search.R

@Composable
internal fun SearchEmptyList(
    searchText: String,
    modifier: Modifier = Modifier,
    onTakePictureClick: OnClick,
) {
    EmptySection(
        modifier = modifier,
        description = stringResource(
            id = R.string.search_empty_description,
            searchText
        ).capitalize(),
        onClick = onTakePictureClick,
    )
}
