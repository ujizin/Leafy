package br.com.devlucasyuji.publish

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.extensions.paddingScreen
import br.com.devlucasyuji.components.ui.Section
import br.com.devlucasyuji.components.ui.animated.AnimatedButtonIcon
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.components.ui.button.Button
import br.com.devlucasyuji.components.ui.image.Icons
import br.com.devlucasyuji.components.ui.textfield.TextField
import br.com.devlucasyuji.publish.viewmodel.PublishUiState
import br.com.devlucasyuji.publish.viewmodel.PublishViewModel

@Composable
fun PublishSection(
    onBackPressed: OnClick,
    onFinishPublish: OnClick,
    viewModel: PublishViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    when (uiState) {
        PublishUiState.Finish -> onFinishPublish()
        PublishUiState.Initial -> PublishContent(
            onBackPressed = onBackPressed,
            onNextClicked = viewModel::sendDraftPlant
        )
    }
}

@Composable
fun PublishContent(
    onBackPressed: OnClick,
    onNextClicked: (title: String, description: String) -> Unit
) {
    Section(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        title = stringResource(R.string.publish_title),
        subTitle = stringResource(R.string.publish_description),
        animation = Animation.SlideToTopLargeDuration,
        trailingIcon = {
            AnimatedButtonIcon(icon = Icons.Back, onClick = onBackPressed)
        },
    ) {
        var title by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 20.dp, end = 20.dp),
            placeholder = stringResource(R.string.title),
            value = title,
            onValueChange = { title = it }
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.25F)
                .padding(top = 16.dp, start = 20.dp, end = 20.dp),
            placeholder = stringResource(R.string.description),
            value = description,
            onValueChange = { description = it },
        )
        Spacer(modifier = Modifier.weight(1F))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .paddingScreen(vertical = 16.dp),
            enabled = title.isNotBlank() && description.isNotBlank(),
            text = stringResource(R.string.next),
            onClick = { onNextClicked(title, description) }
        )
    }
}
