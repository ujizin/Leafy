package com.ujizin.leafy.components.ui.animated.animation


data class Animation internal constructor(
    val direction: Animate.Direction,
    val delayMillis: Int = 0,
    val durationMillis: Int = MediumDuration,
) {
    companion object {
        const val MediumDelay = 500
        const val LargeDelay = 1000

        const val SmallDuration = 500
        private const val MediumDuration = 750
        private const val LargeDuration = 1_000

        // Default animations
        val None = Animation(Animate.Direction.None)
        val SlideToStart = Animation(Animate.Direction.Start)
        val SlideToTop = Animation(Animate.Direction.Top)
        val SlideToEnd = Animation(Animate.Direction.End)
        val SlideToBottom = Animation(Animate.Direction.Bottom)

        val SlideToTopLargeDuration = SlideToTop.copy(durationMillis = LargeDuration)
    }
}
