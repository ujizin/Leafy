package com.ujizin.preferences

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.devlucasyuji.components.extensions.capitalize
import br.com.devlucasyuji.components.extensions.paddingScreen
import br.com.devlucasyuji.components.ui.button.Button
import br.com.devlucasyuji.components.ui.selector.Selector
import br.com.devlucasyuji.components.ui.textfield.Placeholder
import br.com.devlucasyuji.components.ui.textfield.TextField
import br.com.devlucasyuji.preferences.R

@Composable
fun UserSelector(
    modifier: Modifier = Modifier,
    nickname: String,
    onNicknameChanged: (String) -> Unit,
) {
    var showModal by remember { mutableStateOf(false) }
    Selector(
        modifier = modifier,
        title = stringResource(R.string.nickname).capitalize(),
        subTitle = nickname,
        showModal = showModal,
        onModalStateChanged = { showModal = it },
    ) {
        UserTextField(
            modifier = Modifier.paddingScreen(vertical = 20.dp),
            label = stringResource(R.string.nickname).capitalize(),
            nickname = nickname,
            onSaveNickname = { nickname ->
                onNicknameChanged(nickname)
                showModal = false
            }
        )
    }
}

@Composable
fun UserTextField(
    modifier: Modifier = Modifier,
    label: String,
    nickname: String,
    onSaveNickname: (String) -> Unit
) {
    Column(modifier) {
        var newNickname by remember { mutableStateOf(nickname) }

        Text(text = label, style = MaterialTheme.typography.titleSmall)
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            value = newNickname,
            placeholder = { Placeholder(text = nickname) },
            onValueChange = { newNickname = it }
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            text = stringResource(R.string.update),
            onClick = { onSaveNickname(newNickname) }
        )
    }
}
