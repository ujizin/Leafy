package com.ujizin.leafy.components.ui.animated

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.ujizin.leafy.components.ui.animated.animation.Animate.Animated
import com.ujizin.leafy.components.ui.animated.animation.Animation
import com.ujizin.leafy.themes.LeafyTheme

@Composable
fun AnimatedText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    capitalize: Boolean = true,
    style: TextStyle = LocalTextStyle.current,
    animation: Animation = Animation.None,
) {
    Animated(animation = animation) {
        Text(
            text = if (capitalize) text.capitalize() else text,
            modifier = modifier,
            color = color,
            style = style,
        )
    }
}

@Preview
@Composable
private fun PreviewTitle() {
    LeafyTheme {
        AnimatedText(text = "Hello Lucas")
    }
}
