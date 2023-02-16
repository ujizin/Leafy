package br.com.devlucasyuji.alarm

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.devlucasyuji.alarm.components.timer_box.TimerBox
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.components.extensions.paddingScreen
import br.com.devlucasyuji.components.ui.Section
import br.com.devlucasyuji.components.ui.animated.AnimatedButtonIcon
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.components.ui.button.Button
import br.com.devlucasyuji.components.ui.image.Icons
import br.com.devlucasyuji.components.ui.selector.ModalSelector
import br.com.devlucasyuji.components.ui.selector.MultiModalSelector
import br.com.devlucasyuji.components.ui.selector.Selector
import kotlinx.coroutines.launch

enum class AlarmSheet {
    Repeat, Ringtone
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AlarmSection(
    onBackPressed: () -> Unit,
    viewModel: AlarmViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    val modalState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )

    var sheetType by remember { mutableStateOf<AlarmSheet?>(null) }

    val ringValues = remember { listOf("rang", "ring") }
    var ringtone by remember { mutableStateOf(ringValues.first()) }

    val repeatValues = stringArrayResource(id = R.array.repeat_values).toList()
    var repeat by remember { mutableStateOf(repeatValues.first()) }

    LaunchedEffect(sheetType) {
        if (sheetType != null) modalState.show()
    }

    BackHandler {
        when {
            modalState.isVisible -> scope.launch { modalState.hide() }
            else -> onBackPressed()
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            val type = sheetType ?: return@ModalBottomSheetLayout run { Box(Modifier.size(1.dp)) }
            when (type) {
                AlarmSheet.Ringtone -> ModalSelector(
                    title = stringResource(R.string.ringtone),
                    currentValue = ringtone,
                    values = ringValues,
                    onValueChanged = { value ->
                        ringtone = value
                        scope.launch { modalState.hide() }
                    },
                )

                AlarmSheet.Repeat -> MultiModalSelector(
                    title = stringResource(R.string.repeat),
                    currentValue = repeat,
                    values = repeatValues,
                    onValueChanged = { value ->
                        repeat = value
                        scope.launch { modalState.hide() }
                    }
                )
            }

        }) {
        AlarmContent(
            modifier = Modifier.fillMaxSize(),
            ringtone = ringtone,
            repeat = repeat,
            onRingtoneClicked = {
                sheetType = AlarmSheet.Ringtone
            },
            onRepeatClicked = {
                sheetType = AlarmSheet.Repeat
            },
            onBackPressed = onBackPressed
        )
    }
}

@Composable
private fun AlarmContent(
    modifier: Modifier = Modifier,
    ringtone: String,
    repeat: String,
    onBackPressed: OnClick,
    onRingtoneClicked: OnClick,
    onRepeatClicked: OnClick,
) {
    Section(
        modifier = modifier,
        title = stringResource(R.string.alarm_title),
        trailingIcon = {
            AnimatedButtonIcon(icon = Icons.Back, onClick = onBackPressed)
        },
        headerAnimation = Animation.None
    ) {
        TimerBox(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
                .paddingScreen(),
            onTimeChange = { _, _ -> }
        )
        Selector(
            modifier = Modifier
                .fillMaxWidth()
                .paddingScreen(vertical = 16.dp),
            title = stringResource(R.string.ringtone),
            currentValue = ringtone,
            onSelectorClicked = onRingtoneClicked
        )
        Selector(
            modifier = Modifier
                .fillMaxWidth()
                .paddingScreen(vertical = 16.dp),
            title = stringResource(R.string.repeat),
            currentValue = repeat,
            onSelectorClicked = onRepeatClicked
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .paddingScreen(),
            text = stringResource(R.string.next),
            onClick = { }
        )
    }
}