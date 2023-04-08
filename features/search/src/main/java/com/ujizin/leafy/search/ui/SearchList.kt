package com.ujizin.leafy.search.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.ui.Modifier
import com.ujizin.leafy.core.ui.components.card.CardPlant
import com.ujizin.leafy.domain.model.Plant

@OptIn(ExperimentalFoundationApi::class)
internal fun LazyStaggeredGridScope.searchItems(
    modifier: Modifier = Modifier,
    data: List<Plant>
) {
    items(key = { it.id }, items = data) { plant ->
        CardPlant(
            modifier = modifier,
            plant = plant,
            onClick = {},
        )
    }
}
