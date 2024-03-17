package com.ujizin.leafy.features.plant.detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.ui.components.animated.AnimatedButtonIcon
import com.ujizin.leafy.core.ui.components.animated.animation.Animation
import com.ujizin.leafy.core.ui.components.image.Icons

@Composable
fun PlantContent(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onSharedClick: () -> Unit,
) {
    Column(modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier.padding(end = 16.dp),
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
            PlantActions(
                modifier = Modifier.align(Alignment.Top),
                onSharedClick = onSharedClick,
            )
        }
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = description,
        )
    }
}

@Composable
private fun PlantActions(
    modifier: Modifier = Modifier,
    onSharedClick: () -> Unit,
) {
    Row(modifier) {
        AnimatedButtonIcon(
            icon = Icons.Shared,
            size = 24.dp,
            onClick = onSharedClick,
            animation = Animation.SlideToTop,
        )
//    AnimatedButtonIcon(
//        icon = Icons.Favorite,
//        size = 24.dp,
//        onClick = { },
//        animation = Animation.SlideToTop
//    )
    }
}
