package br.com.devlucasyuji.components.ui.animated.animation

import br.com.devlucasyuji.components.ui.animated.animation.Animate.DEFAULT_DURATION_MILLIS

data class Animation internal constructor(
    val direction: Animate.Direction,
    val delayMillis: Int = 0,
    val durationMillis: Int = DEFAULT_DURATION_MILLIS,
) {
    companion object {
        val None = Animation(Animate.Direction.None)
        val SlideToStart = Animation(Animate.Direction.Start)
        val SlideToTop = Animation(Animate.Direction.Top)
        val SlideToEnd = Animation(Animate.Direction.End)
        val SlideToBottom = Animation(Animate.Direction.Bottom)

        const val SmallDelay = 250
        const val MediumDelay = 500
        const val LargeDelay = 1000
    }
}
