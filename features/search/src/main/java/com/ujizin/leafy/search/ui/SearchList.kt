package com.ujizin.leafy.search.ui

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.ujizin.leafy.core.ui.components.card.CardPlant
import com.ujizin.leafy.domain.model.Plant

internal fun LazyListScope.searchItems(
    modifier: Modifier = Modifier,
    data: List<Plant>,
    onPlantClick: (Long) -> Unit,
    onSharedClick: (Plant) -> Unit,
) {
    items(key = { it.id }, items = data) { plant ->
        CardPlant(
            modifier = modifier,
            plant = plant,
            onClick = { onPlantClick(plant.id) },
            onSharedClick = { onSharedClick(plant) },
        )
    }
}
