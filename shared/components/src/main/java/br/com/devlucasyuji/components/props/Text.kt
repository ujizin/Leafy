package br.com.devlucasyuji.components.props

import androidx.compose.ui.text.TextStyle
import br.com.devlucasyuji.components.extensions.Empty

data class Text(
    val text: String = String.Empty,
    val style: TextStyle = TextStyle()
)