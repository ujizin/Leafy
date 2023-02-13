package br.com.devlucasyuji.alarm

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.alarm.components.timer_box.TimerBox
import br.com.devlucasyuji.components.extensions.section
import br.com.devlucasyuji.components.ui.Section
import br.com.devlucasyuji.components.ui.animated.AnimatedButtonIcon
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.components.ui.button.Button
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
            AnimatedButtonIcon(icon = Icons.Back, onClick = onBackPressed)
        },
        headerAnimation = Animation.None
    ) {
        TimerBox(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            onTimeChange = { _, _ -> }
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            text = stringResource(R.string.next),
            onClick = { }
        )
    }
}
