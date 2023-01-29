package br.com.devlucasyuji.publish

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        title = stringResource(R.string.publish_title),
        subTitle = stringResource(R.string.publish_description),
        trailingIcon = {
            AnimatedIcon(icon = Icons.Back, onClick = onBackPressed)
        },
    ) {
        var title by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            placeholder = stringResource(R.string.title),
            value = title,
            onValueChange = { title = it }
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.25F)
                .padding(top = 16.dp),
            placeholder = stringResource(R.string.description),
            value = description,
            onValueChange = { description = it },
        )
        Spacer(modifier = Modifier.weight(1F))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            text = stringResource(R.string.next),
            onClick = { }
        )
    }
}
