package com.ujizin.leafy.alarm.alarm

import android.media.RingtoneManager
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.ujizin.leafy.alarm.alarm.components.timerbox.TimerBox
import com.ujizin.leafy.alarm.extensions.alarmManager
import com.ujizin.leafy.alarm.extensions.hasAlarmPermission
import com.ujizin.leafy.alarm.extensions.startAlarmPermission
import com.ujizin.leafy.alarm.model.RepeatMode
import com.ujizin.leafy.core.ui.components.Section
import com.ujizin.leafy.core.ui.components.animated.AnimatedButtonIcon
import com.ujizin.leafy.core.ui.components.animated.animation.Animation
import com.ujizin.leafy.core.ui.components.button.Button
import com.ujizin.leafy.core.ui.components.image.Icons
import com.ujizin.leafy.core.ui.components.selector.ModalSelector
import com.ujizin.leafy.core.ui.components.selector.MultiModalSelector
import com.ujizin.leafy.core.ui.components.selector.Selector
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.ui.extensions.paddingScreen
import com.ujizin.leafy.core.ui.state.observeAsState
import com.ujizin.leafy.domain.model.Ringtone
import com.ujizin.leafy.domain.model.orDefault
import com.ujizin.leafy.features.alarm.R

@Composable
fun AlarmSection(
    onBackPressed: OnClick,
    viewModel: AlarmViewModel = hiltViewModel(),
    onSaved: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    val repeatValues = remember { RepeatMode.values().toList() }
    val ringtones by remember {
        derivedStateOf { (uiState as? AlarmUiState.Initialized)?.ringtones.orEmpty() }
    }
    var ringtone by remember(ringtones) {
        mutableStateOf(ringtones.firstOrNull().orDefault(context))
    }
    var repeatMode by remember { mutableStateOf(repeatValues.first()) }

    var hours by remember { mutableStateOf(0) }
    var minutes by remember { mutableStateOf(0) }

    LaunchedEffect(viewModel) { viewModel.setupRingtones() }

    LaunchedEffect(context.alarmManager.hasAlarmPermission) {
        context.startAlarmPermission()
    }

    AlarmScreen(
        modifier = Modifier.fillMaxSize(),
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
                onPlantPublished = onSaved,
            )
        },
    )
}

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
    val ringtonePlayer = remember(ringtone) {
        RingtoneManager.getRingtone(context, ringtone.uri)
    }
    var ringtoneShowModal by remember { mutableStateOf(false) }
    var repeatShowModal by remember { mutableStateOf(false) }

    RingtoneEffect(ringtonePlayer, ringtoneShowModal)

    AlarmContent(
        modifier = modifier,
        ringtone = ringtone.title,
        repeat = stringResource(repeat.display),
        ringtoneContent = {
            ModalSelector(
                title = stringResource(R.string.ringtone),
                currentValue = ringtone.title,
                values = remember { ringtoneValues.map { it.title } },
                onValueChanged = { value ->
                    ringtonePlayer.stop()
                    onRingtoneChanged(ringtoneValues.first { it.title == value })
                },
            )
        },
        repeatShowModal = repeatShowModal,
        ringtoneShowModal = ringtoneShowModal,
        repeatContent = {
            MultiModalSelector(
                title = stringResource(R.string.repeat),
                currentValue = stringResource(id = repeat.display),
                values = repeatModeValues.map { stringResource(it.display) },
                onValueChanged = { value ->
                    onRepeatChanged(RepeatMode.getByDisplayValue(context, value))
                    repeatShowModal = false
                },
            )
        },
        onRepeatModalStateChanged = { isVisible -> repeatShowModal = isVisible },
        onRingtoneModalStateChanged = { isVisible -> ringtoneShowModal = isVisible },
        onTimeChanged = onTimeChanged,
        onBackPressed = onBackPressed,
        onSaveClicked = onSaveClicked,
    )
}

@Composable
private fun AlarmContent(
    modifier: Modifier = Modifier,
    ringtone: String,
    repeat: String,
    onBackPressed: OnClick,
    repeatShowModal: Boolean,
    onRepeatModalStateChanged: (Boolean) -> Unit,
    ringtoneShowModal: Boolean,
    onRingtoneModalStateChanged: (Boolean) -> Unit,
    ringtoneContent: @Composable ColumnScope.() -> Unit,
    repeatContent: @Composable ColumnScope.() -> Unit,
    onTimeChanged: (hours: Int, minutes: Int) -> Unit,
    onSaveClicked: OnClick,
) {
    Section(
        modifier = modifier,
        title = stringResource(R.string.alarm_title),
        trailingIcon = {
            AnimatedButtonIcon(icon = Icons.Back, onClick = onBackPressed)
        },
        headerAnimation = Animation.None,
    ) {
        TimerBox(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
                .paddingScreen(),
            onTimeChange = onTimeChanged,
        )
        Selector(
            modifier = Modifier
                .fillMaxWidth()
                .paddingScreen(vertical = 16.dp),
            title = stringResource(R.string.ringtone),
            onModalStateChanged = onRingtoneModalStateChanged,
            showModal = ringtoneShowModal,
            value = ringtone,
            content = ringtoneContent,
        )
        Selector(
            modifier = Modifier
                .fillMaxWidth()
                .paddingScreen(vertical = 16.dp),
            title = stringResource(R.string.repeat),
            showModal = repeatShowModal,
            onModalStateChanged = onRepeatModalStateChanged,
            value = repeat,
            content = repeatContent,
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .paddingScreen(),
            text = stringResource(R.string.save),
            onClick = onSaveClicked,
        )
    }
}

@Composable
fun RingtoneEffect(ringtonePlayer: android.media.Ringtone, isModalVisible: Boolean) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val lifecycleState by lifecycle.observeAsState()

    LaunchedEffect(lifecycleState) {
        if (
            lifecycleState == Lifecycle.Event.ON_STOP ||
            lifecycleState == Lifecycle.Event.ON_PAUSE
        ) {
            ringtonePlayer.stop()
        }
    }

    LaunchedEffect(ringtonePlayer) {
        if (isModalVisible && !ringtonePlayer.isPlaying) ringtonePlayer.play()
    }

    LaunchedEffect(isModalVisible) {
        if (!isModalVisible) ringtonePlayer.stop()
    }
}
