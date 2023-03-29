package com.ujizin.leafy.search.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ujizin.leafy.components.ui.card.BoxImage
import com.ujizin.leafy.components.ui.label.TitleRow
import com.ujizin.leafy.domain.model.Plant

@OptIn(ExperimentalFoundationApi::class)
internal fun LazyStaggeredGridScope.searchItems(
    modifier: Modifier = Modifier,
    data: List<Plant>,
) {
    items(key = { it.id }, items = data) { plant ->
        BoxImage(
            modifier = modifier,
            data = plant.file,
            contentDescription = plant.title,
        ) {
            TitleRow(
                title = plant.title,
                titleStyle = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                subTitleStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                subTitle = plant.description,
                verticalAlignment = Alignment.Bottom
            )
        }
    }
}