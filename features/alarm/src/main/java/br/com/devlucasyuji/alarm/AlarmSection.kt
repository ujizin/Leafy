package br.com.devlucasyuji.alarm

import android.media.RingtoneManager
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import br.com.devlucasyuji.domain.model.Ringtone
import br.com.devlucasyuji.domain.model.orDefault
import br.com.devlucasyuji.model.RepeatMode
import kotlinx.coroutines.launch

enum class AlarmSheet {
    Repeat, Ringtone, Hidden
}

@Composable
fun AlarmSection(
    onBackPressed: OnClick,
    viewModel: AlarmViewModel = hiltViewModel(),
    onSaved: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(viewModel) { viewModel.setupRingtones() }

    val repeatValues = remember { RepeatMode.values().toList() }
    val ringtones by remember { derivedStateOf { (uiState as? AlarmUiState.Initialized)?.ringtones.orEmpty() } }
    var ringtone by remember(ringtones) { mutableStateOf(ringtones.firstOrNull().orDefault(context)) }
    var repeatMode by remember { mutableStateOf(repeatValues.first()) }

    var hours by remember { mutableStateOf(0) }
    var minutes by remember { mutableStateOf(0) }

    AlarmScreen(
        ringtoneValues = ringtones,
        repeatModeValues = repeatValues,
        onBackPressed = onBackPressed,
        repeat = repeatMode,
        ringtone = ringtone,
        onRingtoneChanged = { ringtone = it },
        onRepeatChanged = { repeatMode = it },
        onTimeChanged = { hour, minute ->
            hours = hour
            minutes = minute
        },
        onSaveClicked = {
            viewModel.addPlantWithAlarm(
                ringtone = ringtone,
                repeatMode = repeatMode,
                hours = hours,
                minutes = minutes,
                onPlantPublished = onSaved
            )
        },
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AlarmScreen(
    modifier: Modifier = Modifier,
    ringtoneValues: List<Ringtone>,
    repeatModeValues: List<RepeatMode>,
    ringtone: Ringtone,
    repeat: RepeatMode,
    onTimeChanged: (hours: Int, minutes: Int) -> Unit,
    onRepeatChanged: (RepeatMode) -> Unit,
    onRingtoneChanged: (Ringtone) -> Unit,
    onBackPressed: OnClick,
    onSaveClicked: OnClick,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val modalState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )
    val ringtonePlayer = remember(ringtone) { RingtoneManager.getRingtone(context, ringtone.uri) }

    var sheetType by remember { mutableStateOf(AlarmSheet.Hidden) }

    RingtoneEffect(ringtonePlayer, modalState.isVisible)

    ModalEffect(modalState, sheetType) { sheetType = AlarmSheet.Hidden }

    BackHandler {
        when {
            modalState.isVisible -> scope.launch { modalState.hide() }
            else -> onBackPressed()
        }
    }

    ModalBottomSheetLayout(modifier = modifier,
        sheetState = modalState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            when (sheetType) {
                AlarmSheet.Ringtone -> ModalSelector(
                    title = stringResource(R.string.ringtone),
                    enabled = !modalState.isAnimationRunning,
                    currentValue = ringtone.title,
                    values = remember { ringtoneValues.map { it.title } },
                    onValueChanged = { value ->
                        ringtonePlayer.stop()
                        onRingtoneChanged(ringtoneValues.first { it.title == value })
                    },
                )
                // TODO add a multiple modal selector for repeat mode on V 2
                AlarmSheet.Repeat -> MultiModalSelector(title = stringResource(R.string.repeat),
                    currentValue = stringResource(id = repeat.display),
                    enabled = !modalState.isAnimationRunning,
                    values = repeatModeValues.map { stringResource(it.display) },
                    onValueChanged = { value ->
                        onRepeatChanged(RepeatMode.getByDisplayValue(context, value))
                        scope.launch { modalState.hide() }
                    })

                AlarmSheet.Hidden -> Box(Modifier.size(1.dp))
            }

        }) {
        AlarmContent(
            modifier = Modifier.fillMaxSize(),
            ringtone = ringtone.title,
            repeat = stringResource(repeat.display),
            onRingtoneClicked = { sheetType = AlarmSheet.Ringtone },
            onRepeatClicked = { sheetType = AlarmSheet.Repeat },
            onTimeChanged = onTimeChanged,
            onBackPressed = onBackPressed,
            onSaveClicked = onSaveClicked
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
    onTimeChanged: (hours: Int, minutes: Int) -> Unit,
    onRepeatClicked: OnClick,
    onSaveClicked: OnClick,
) {
    Section(
        modifier = modifier, title = stringResource(R.string.alarm_title), trailingIcon = {
            AnimatedButtonIcon(icon = Icons.Back, onClick = onBackPressed)
        }, headerAnimation = Animation.None
    ) {
        TimerBox(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
                .paddingScreen(),
            onTimeChange = onTimeChanged
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
            text = stringResource(R.string.save),
            onClick = onSaveClicked
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ModalEffect(
    modalState: ModalBottomSheetState, sheetType: AlarmSheet, onModalHidden: () -> Unit
) {
    LaunchedEffect(sheetType) {
        if (sheetType != AlarmSheet.Hidden) modalState.show()
    }

    LaunchedEffect(modalState.isAnimationRunning) {
        if (!modalState.isAnimationRunning && !modalState.isVisible) {
            onModalHidden()
        }
    }
}

@Composable
fun RingtoneEffect(ringtonePlayer: android.media.Ringtone, modalState: Boolean) {
    LaunchedEffect(ringtonePlayer) {
        if (modalState) ringtonePlayer.play()
    }

    LaunchedEffect(modalState) {
        if (!modalState) ringtonePlayer.stop()
    }
}
