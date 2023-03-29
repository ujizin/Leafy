package com.ujizin.leafy.components.ui.image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

/**
 * Image composable with the blur image background.
 * */
@Composable
fun BlurAsyncImage(
    modifier: Modifier = Modifier,
    model: Any,
    contentDescription: String?
) {
    Box(modifier) {
//        var imageState by remember { mutableStateOf<AsyncImagePainter.State?>(null) }
//        Cloudy(
//            modifier = Modifier.fillMaxSize(),
//            radius = 25,
//            key1 = imageState,
//        ) {
//        AsyncImage(
//            modifier = Modifier.fillMaxSize(),
//            model = model,
//            onState = { state -> imageState = state },
//            contentScale = ContentScale.Crop,
//            contentDescription = contentDescription,
//        )
//        }

        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = model,
            contentScale = ContentScale.Fit,
            contentDescription = contentDescription,
        )
    }
}
