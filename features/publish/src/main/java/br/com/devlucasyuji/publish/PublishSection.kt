package br.com.devlucasyuji.publish

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.ui.Section
import br.com.devlucasyuji.components.ui.animated.AnimatedIcon
import br.com.devlucasyuji.components.ui.button.Button
import br.com.devlucasyuji.components.ui.image.Icons
import br.com.devlucasyuji.components.ui.textfield.TextField
import br.com.devlucasyuji.publish.viewmodel.PublishViewModel

@Composable
fun PublishSection(onBackPressed: OnClick, viewModel: PublishViewModel = hiltViewModel()) {
    Section(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        title = stringResource(R.string.publish_title),
        subTitle = stringResource(R.string.publish_description),
        trailingIcon = {
            AnimatedIcon(icon = Icons.Back, onClick = onBackPressed)
        },
    ) {
        var title by remember { mutableStateOf("") }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = title, onValueChange = { title = it }
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.publish),
            onClick = { }
        )
    }
}
