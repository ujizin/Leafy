package br.com.devlucasyuji.alarm.components.alarm_box

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.devlucasyuji.alarm.components.AutoSizeText
import kotlin.math.floor

@Composable
fun AlarmBox(
    modifier: Modifier = Modifier,
    onTimeChange: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(64.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TimeBox(
            modifier = Modifier.weight(1F),
            time = Time.Hour,
            horizontalAlignment = Alignment.End,
        )
        TimeBox(
            modifier = Modifier.weight(1F),
            time = Time.Minutes,
            horizontalAlignment = Alignment.Start,
        )
    }
}

/**
 * @param quantity how many numbers it will appear
 * */
@Composable
private fun TimeBox(
    modifier: Modifier = Modifier,
    time: Time,
    quantity: Int = 5,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    val state = rememberLazyListState()
    val quantityMiddle = remember(quantity) { floor(quantity.toDouble() / 2).toInt() }
    LazyColumn(
        modifier = modifier,
        state = state,
        horizontalAlignment = horizontalAlignment
    ) {
        // maintain endless
        items(Int.MAX_VALUE) { listIndex ->
            val index = listIndex % time.numbers.size
            val number = time.numbers[index]
            val middle by remember { derivedStateOf { state.firstVisibleItemIndex + quantityMiddle } }
            val endIndex by remember { derivedStateOf { state.firstVisibleItemIndex + (quantity - 1) } }

            val value by remember(listIndex) {
                derivedStateOf {
                    when (listIndex) {
                        state.firstVisibleItemIndex, endIndex -> 0.25F
                        middle -> 1F
                        in state.firstVisibleItemIndex until middle,
                        in middle until endIndex -> 0.5F

                        else -> 0F
                    }
                }
            }

            val animatedValue by animateFloatAsState(targetValue = value)
            val scaleMultiplier = remember(Unit) { 1.4F }

            AutoSizeText(
                modifier = Modifier
                    .alpha(value)
                    .scale((animatedValue * scaleMultiplier).coerceAtLeast(0.5F)),
                text = number,
                textStyle = TextStyle(
                    fontSize = 48.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                )
            )
        }
    }

    LaunchedEffect(Unit) {
        state.scrollToItem(Int.MAX_VALUE / 2)
    }

    LaunchedEffect(state.isScrollInProgress) {
        if (!state.isScrollInProgress) state.animateScrollToItem(state.firstVisibleItemIndex)
    }
}
