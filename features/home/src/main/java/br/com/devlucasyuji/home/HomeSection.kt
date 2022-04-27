package br.com.devlucasyuji.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.devlucasyuji.components.props.Icons
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.atomic.atoms.ButtonIcon
import br.com.devlucasyuji.components.atomic.organisms.card.CardSize
import br.com.devlucasyuji.components.atomic.organisms.card.ImageCard
import br.com.devlucasyuji.components.atomic.organisms.header
import br.com.devlucasyuji.components.props.Text

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavController.HomeSection() {
    LazyColumn {
        header(
            title = Text("Hi Lucas!"),
            subTitle = Text("Welcome back"),
            leadingIcon = ButtonIcon(Icons.Hamburger, Animation.SlideToEnd),
            trailingIcon = ButtonIcon(Icons.Magnifier, Animation.SlideToStart)
        )
        item {
            Spacer(Modifier.height(16.dp))
        }
        items(16, key = { it }) {
            ImageCard(
                modifier = Modifier
                    .animateItemPlacement()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                cardModifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(0.0F),
                                Color.Black.copy(0.75F)
                            )
                        ),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(12.dp),
                data = "https://viagemeturismo.abril.com.br/wp-content/uploads/2017/02/torre-eiffel.jpeg",
                contentDescription = null,
                title = Text("Torre Eiffel", TextStyle(color = Color.White)),
                subTitle = Text("Torre Eiffel", TextStyle(color = Color.White)),
                size = CardSize.Large,
                buttonIcons = arrayOf(
                    ButtonIcon(Icons.Settings)
                )
            )
        }
        item {
            Spacer(Modifier.height(32.dp))
        }
    }
}
