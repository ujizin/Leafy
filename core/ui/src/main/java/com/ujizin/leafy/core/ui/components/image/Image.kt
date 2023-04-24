package com.ujizin.leafy.core.ui.components.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun Image(
    modifier: Modifier = Modifier,
    model: Any,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String?,
) {
    AsyncImage(
        modifier = modifier,
        model = model,
        contentScale = contentScale,
        contentDescription = contentDescription,
    )
}
