package br.com.devlucasyuji.search.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.ui.animated.AnimatedButtonIcon
import br.com.devlucasyuji.components.ui.card.BoxImage
import br.com.devlucasyuji.components.ui.card.CardSize
import br.com.devlucasyuji.components.ui.image.Icons
import br.com.devlucasyuji.components.ui.label.TitleRow
import br.com.devlucasyuji.domain.model.Plant

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun SearchList(data: List<Plant>, isKeyboardOpen: Boolean) {
    val state = rememberLazyStaggeredGridState()

    LazyVerticalStaggeredGrid(
        modifier = Modifier.padding(horizontal = 20.dp),
        state = state,
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = data) { plant ->
            BoxImage(
                modifier = Modifier
                    //.animateItemPlacement()
//                    .heightIn(min = CardSize.Small.height, max = CardSize.Large.height)
                    .fillMaxWidth()
                    .heightIn(min = 1.dp, max = CardSize.Large.height),
                data = plant.file,
                contentDescription = plant.title,
            ) {
                TitleRow(
                    title = plant.title,
                    titleStyle = MaterialTheme.typography.titleLarge.copy(color = Color.White),
                    subTitleStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                    subTitle = plant.description,
                    verticalAlignment = Alignment.Bottom
                ) {
                    AnimatedButtonIcon(
                        icon = Icons.Shared,
                        tint = Color.White,
                        size = 24.dp
                    )
                }
            }
        }
        item {
            Spacer(Modifier.size(if (isKeyboardOpen) 32.dp else 0.dp))
        }
    }
}