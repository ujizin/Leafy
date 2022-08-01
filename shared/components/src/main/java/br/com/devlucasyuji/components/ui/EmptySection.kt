package br.com.devlucasyuji.components.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.R
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.extensions.capitalize
import br.com.devlucasyuji.components.ui.button.Button
import br.com.devlucasyuji.components.ui.image.Icons
import br.com.devlucasyuji.themes.CameraReminderTheme

@Composable
fun EmptySection(
    modifier: Modifier = Modifier,
    @StringRes descriptionRes: Int = R.string.empty_photo,
    @StringRes buttonRes: Int = R.string.take_a_picture,
    icons: Icons = Icons.Folder,
    onClick: OnClick,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.defaultMinSize(minWidth = 128.dp, minHeight = 96.dp),
            painter = painterResource(id = icons.resId),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            contentDescription = stringResource(id = icons.descriptionRes),
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(id = descriptionRes).capitalize(),
            textAlign = TextAlign.Center,
        )
        Button(text = stringResource(id = buttonRes).capitalize(), onClick = onClick)
    }
}

@Preview
@Composable
fun PreviewEmptySection() {
    CameraReminderTheme {
        EmptySection {}
    }
}