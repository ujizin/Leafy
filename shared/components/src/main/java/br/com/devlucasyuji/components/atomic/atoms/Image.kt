package br.com.devlucasyuji.components.atomic.atoms

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
internal fun Image(
    modifier: Modifier = Modifier,
    data: Any? = null,
    contentDescription: String?,
) {
    CoilImage(
        modifier = modifier,
        contentScale = ContentScale.Crop,
        imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(data)
            .build(),
        shimmerParams = ShimmerParams(baseColor = Color.Gray, highlightColor = Color.LightGray),
        contentDescription = contentDescription
    )
}