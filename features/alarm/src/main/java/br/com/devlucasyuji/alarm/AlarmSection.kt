package br.com.devlucasyuji.alarm

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.devlucasyuji.alarm.components.timer_box.TimerBox
import br.com.devlucasyuji.components.extensions.paddingScreen
import br.com.devlucasyuji.components.ui.Section
import br.com.devlucasyuji.components.ui.animated.AnimatedButtonIcon
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.components.ui.button.Button
import br.com.devlucasyuji.components.ui.image.Icons
import br.com.devlucasyuji.components.ui.selector.ModalSelector
import br.com.devlucasyuji.components.ui.selector.Selector
import kotlinx.coroutines.launch

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
    var currentRing by remember { mutableStateOf("teste") }
    val selectorValues = remember { listOf("teste", "teste2") }
    ModalBottomSheetLayout(
        sheetState = modalState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            ModalSelector(
                modifier = Modifier,
                title = "Ring",
                currentValue = currentRing,
                values = selectorValues,
                onValueChanged = { value ->
                    scope.launch {
                        modalState.hide()
                        currentRing = value
                    }
                },
            )
        }) {
        Section(
            modifier = Modifier.fillMaxSize(),
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
                    .paddingScreen(vertical = 24.dp),
                title = "alarm",
                currentValue = currentRing,
                values = selectorValues,
                onSelectorClicked = {
                    scope.launch { modalState.show() }
                }
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
}
