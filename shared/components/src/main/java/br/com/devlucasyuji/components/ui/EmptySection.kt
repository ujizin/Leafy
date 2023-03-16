package br.com.devlucasyuji.components.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextOverflow
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
    description: String = stringResource(id = R.string.empty_plant).capitalize(),
    buttonTitle: String = stringResource(id = R.string.take_a_picture).capitalize(),
    enabled: Boolean = true,
    icons: Icons = Icons.Leaf,
    onClick: OnClick,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.defaultMinSize(minWidth = 128.dp, minHeight = 96.dp),
            painter = painterResource(id = icons.resId),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            contentDescription = stringResource(id = icons.descriptionRes),
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = description.capitalize(),
            textAlign = TextAlign.Center,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        Button(
            text = buttonTitle.capitalize(),
            enabled = enabled,
            onClick = onClick
        )
    }
}

@Preview
@Composable
fun PreviewEmptySection() {
    CameraReminderTheme {
        EmptySection {}
    }
}