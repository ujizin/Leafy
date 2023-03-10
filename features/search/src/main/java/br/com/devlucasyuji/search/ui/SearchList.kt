package br.com.devlucasyuji.search.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
internal fun SearchList(data: List<Plant>) {
    val state = rememberLazyGridState()
    LazyVerticalGrid(
        state = state,
        columns = GridCells.Fixed(2)
    ) {
        itemsIndexed(items = data) { index, plant ->
            Column(Modifier.offset(y = if (index % 2 == 1) 32.dp else (-32).dp)) {
                BoxImage(
                    modifier = Modifier
                        .animateItemPlacement()
                        .height(CardSize.Large.height)
                        .padding(horizontal = 20.dp, vertical = 8.dp),
                    data = plant.file,
                    contentDescription = plant.description,
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
        }
    }
}