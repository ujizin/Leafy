package br.com.devlucasyuji.alarm

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.devlucasyuji.components.extensions.section
import br.com.devlucasyuji.components.ui.Section
import br.com.devlucasyuji.components.ui.animated.AnimatedIcon
import br.com.devlucasyuji.components.ui.image.Icons

@Composable
fun AlarmSection(
    onBackPressed: () -> Unit,
) {
    Section(
        modifier = Modifier
            .fillMaxSize()
            .section(),
        title = stringResource(R.string.alarm_title),
        trailingIcon = {
            AnimatedIcon(icon = Icons.Back, onClick = onBackPressed)
        }
    ) {

    }
}
