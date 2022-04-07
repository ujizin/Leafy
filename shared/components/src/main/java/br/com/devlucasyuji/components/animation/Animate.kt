package br.com.devlucasyuji.components.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.IntOffset

object Animate {

    internal const val DEFAULT_DURATION_MILLIS = 750

    enum class Direction {
        None, Start, Top, End, Bottom;
    }

    private fun Animation.enterTransition(): EnterTransition {
        val animationSpec = tween<IntOffset>(durationMillis, delayMillis, FastOutSlowInEasing)
        return when (direction) {
            Direction.Start -> slideInHorizontally(
                animationSpec = animationSpec,
                initialOffsetX = { it / 2 })
            Direction.Top -> slideInVertically(
                animationSpec = animationSpec,
                initialOffsetY = { it / 2 })
            Direction.End -> slideInHorizontally(animationSpec = animationSpec)
            Direction.Bottom -> slideInVertically(animationSpec = animationSpec)
            Direction.None -> EnterTransition.None
        }
    }

    private fun fadeInEaseInOut(durationMillis: Int, delayMillis: Int) = fadeIn(
        animationSpec = tween(durationMillis, delayMillis, FastOutSlowInEasing)
    )

    @Composable
    internal fun Animation.Animated(
        content: @Composable () -> Unit,
    ) {
        if (direction == Direction.None) {
            content()
            return
        }
        val visibleState = remember { MutableTransitionState(false).apply { targetState = true } }
        AnimatedVisibility(
            visibleState = visibleState,
            enter = enterTransition() + fadeInEaseInOut(durationMillis, delayMillis),
        ) { content() }
    }
}
