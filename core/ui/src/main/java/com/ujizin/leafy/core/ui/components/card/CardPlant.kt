package com.ujizin.leafy.core.ui.components.card

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.ui.components.animated.AnimatedButtonIcon
import com.ujizin.leafy.core.ui.components.image.Icons
import com.ujizin.leafy.core.ui.components.label.TitleRow
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.domain.model.Plant

@Composable
fun CardPlant(
    modifier: Modifier = Modifier,
    plant: Plant,
    onClick: OnClick,
    onSharedClick: OnClick,
) {
    CardImage(
        modifier = modifier,
        data = plant.filePath,
        contentDescription = plant.title,
        onClick = onClick,
    ) {
        TitleRow(
            title = plant.title,
            titleStyle = MaterialTheme.typography.titleLarge.copy(color = Color.White),
            subTitleStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            subTitle = plant.description,
            verticalAlignment = Alignment.Bottom,
        ) {
            AnimatedButtonIcon(
                icon = Icons.Shared,
                tint = Color.White,
                size = 24.dp,
                background = Color.Transparent,
                onClick = onSharedClick,
            )
        }
    }
}
