package br.com.devlucasyuji.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.atomic.atoms.ButtonIcon
import br.com.devlucasyuji.components.atomic.organisms.Header
import br.com.devlucasyuji.components.atomic.organisms.Section
import br.com.devlucasyuji.components.atomic.organisms.card.CardSize
import br.com.devlucasyuji.components.atomic.organisms.card.ImageCard
import br.com.devlucasyuji.components.extensions.innerShadow
import br.com.devlucasyuji.components.props.Icons
import br.com.devlucasyuji.components.props.Shadow
import br.com.devlucasyuji.components.props.Text

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavController.HomeSection(viewModel: HomeViewModel = hiltViewModel()) {
    Section(
        header = Header(
            title = Text("Hi Lucas!"),
            subTitle = Text("Welcome back"),
            leadingIcon = ButtonIcon(Icons.Hamburger, Animation.SlideToEnd),
            trailingIcon = ButtonIcon(Icons.Magnifier, Animation.SlideToStart)
        ),
    ) {
        items(16, key = { it }) {
            ImageCard(
                modifier = Modifier
                    .animateItemPlacement()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                cardModifier = Modifier
                    .innerShadow(Shadow.Black, RoundedCornerShape(4.dp))
                    .padding(12.dp),
                data = "https://viagemeturismo.abril.com.br/wp-content/uploads/2017/02/torre-eiffel.jpeg",
                contentDescription = null,
                title = Text("Torre Eiffel", TextStyle(color = Color.White)),
                subTitle = Text("Torre Eiffel", TextStyle(color = Color.White)),
                size = CardSize.Large,
                buttonIcons = arrayOf(
                    ButtonIcon(Icons.Settings, tint = Color.White)
                )
            )
        }
    }
}
