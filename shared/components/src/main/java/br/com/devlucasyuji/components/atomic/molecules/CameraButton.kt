package br.com.devlucasyuji.components.atomic.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.R
import br.com.devlucasyuji.components.animation.Animate.Animated
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.extensions.OnClick

@Composable
internal fun CameraButton(onClick: OnClick) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-32).dp),
        contentAlignment = Alignment.Center
    ) {
        Animation.SlideToBottom.copy(delayMillis = 750).Animated {
            IconButton(
                modifier = Modifier
                    .size(72.dp)
                    .background(Color.White, CircleShape),
                onClick = onClick
            ) {
                Animation.SlideToTop.copy(delayMillis = 1000).Animated {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.drawable.camera),
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = null
                    )
                }
            }
        }
    }
}