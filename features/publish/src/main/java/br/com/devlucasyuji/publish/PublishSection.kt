package br.com.devlucasyuji.publish

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.devlucasyuji.publish.viewmodel.PublishViewModel
import coil.compose.AsyncImage
import java.io.File

@Composable
fun PublishSection(viewModel: PublishViewModel = hiltViewModel()) {
    Box(Modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = File(viewModel.image),
            contentDescription = null
        )
    }
}
