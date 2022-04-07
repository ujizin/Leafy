package br.com.devlucasyuji.components.atomic.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.devlucasyuji.components.atomic.atoms.Label
import br.com.devlucasyuji.components.atomic.atoms.Title
import br.com.devlucasyuji.components.extensions.Empty

@Composable
fun Header(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String = String.Empty
) {
    Column(modifier) {
        Title(title)
        if (subTitle != String.Empty) {
            Label(subTitle)
        }
    }
}
