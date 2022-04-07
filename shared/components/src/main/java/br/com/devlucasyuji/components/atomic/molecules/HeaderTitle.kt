package br.com.devlucasyuji.components.atomic.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.animation.Animate
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.atomic.atoms.Label
import br.com.devlucasyuji.components.atomic.atoms.Title
import br.com.devlucasyuji.components.extensions.Empty

@Composable
fun HeaderTitle(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(horizontal = 20.dp),
    title: String,
    subTitle: String = String.Empty
) {
    Column(modifier.padding(paddingValues)) {
        Title(title, animation = Animation.Top)
        if (subTitle != String.Empty) {
            Label(
                text = subTitle,
                animation = Animation(
                    direction = Animate.Direction.Top,
                    delayMillis = 500,
                )
            )
        }
    }
}
