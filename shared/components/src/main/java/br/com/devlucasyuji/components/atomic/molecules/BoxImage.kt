package br.com.devlucasyuji.components.atomic.molecules

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.devlucasyuji.components.atomic.atoms.Image
import br.com.devlucasyuji.components.extensions.Content

@Composable
fun BoxImage(
    modifier: Modifier = Modifier,
    data: Any?,
    contentDescription: String?,
    contentAlignment: Alignment = Alignment.TopStart,
    boxModifier: Modifier = Modifier,
    content: @Composable Content
) {
    Box(modifier = modifier) {
        Image(Modifier.fillMaxSize(), data, contentDescription)
        Box(Modifier.fillMaxSize().then(boxModifier), contentAlignment = contentAlignment) {
            content()
        }
    }
}
