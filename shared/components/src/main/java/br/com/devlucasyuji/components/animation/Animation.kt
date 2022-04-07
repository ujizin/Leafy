package br.com.devlucasyuji.components.animation

import br.com.devlucasyuji.components.animation.Animate.DEFAULT_DURATION_MILLIS

data class Animation(
    val direction: Animate.Direction,
    val delayMillis: Int = 0,
    val durationMillis: Int = DEFAULT_DURATION_MILLIS,
) {
    companion object {
        val None = Animation(Animate.Direction.None)
        val Start = Animation(Animate.Direction.Start)
        val Top = Animation(Animate.Direction.Top)
        val End = Animation(Animate.Direction.End)
        val Bottom = Animation(Animate.Direction.Bottom)
    }
}