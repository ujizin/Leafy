package com.ujizin.leafy.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.ui.components.EmptySection
import com.ujizin.leafy.core.ui.components.Section
import com.ujizin.leafy.core.ui.components.animated.AnimatedButtonIcon
import com.ujizin.leafy.core.ui.components.animated.animation.Animation
import com.ujizin.leafy.core.ui.components.card.BoxImage
import com.ujizin.leafy.core.ui.components.card.CardSize
import com.ujizin.leafy.core.ui.components.image.Icons
import com.ujizin.leafy.core.ui.components.label.TitleRow
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.ui.extensions.capitalize
import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.features.home.R

@Composable
internal fun HomeSection(
    nickname: String,
    plants: List<Plant>,
    onEmptyPlantClick: OnClick,
    onSearchClick: OnClick,
    onDrawerClick: OnClick
) {
    NavLazyColumn {
        item {
            Section(
                title = stringResource(
                    id = R.string.hello_user,
                    nickname.capitalize(),
                ).capitalize(),
                subTitle = stringResource(id = R.string.welcome_back).capitalize(),
                leadingIcon = {
                    AnimatedButtonIcon(
                        icon = Icons.Hamburger,
                        animation = Animation.SlideToEnd,
                        onClick = onDrawerClick,
                    )
                },
                trailingIcon = {
                    AnimatedButtonIcon(
                        icon = Icons.Magnifier,
                        animation = Animation.SlideToStart,
                        onClick = onSearchClick,
                    )
                },
            )
        }
        when {
            plants.isEmpty() -> item {
                EmptySection(
                    modifier = Modifier.padding(vertical = 32.dp, horizontal = 20.dp),
                    onClick = onEmptyPlantClick,
                )
            }

            else -> items(plants, key = { it.id }) { HomePlantCard(it) }
        }
    }
}

@Composable
fun NavLazyColumn(content: LazyListScope.() -> Unit) {
    LazyColumn {
        content()
        item { Spacer(Modifier.padding(64.dp)) }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun LazyItemScope.HomePlantCard(plant: Plant) {
    BoxImage(
        modifier = Modifier
            .animateItemPlacement()
            .height(CardSize.Medium.height)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        data = plant.file,
        contentDescription = plant.description,
    ) {
        TitleRow(
            title = plant.title,
            titleStyle = MaterialTheme.typography.titleLarge.copy(color = Color.White),
            subTitleStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            subTitle = plant.description,
            verticalAlignment = Alignment.Bottom,
        ) {
            AnimatedButtonIcon(icon = Icons.Shared, tint = Color.White, size = 24.dp)
        }
    }
}
