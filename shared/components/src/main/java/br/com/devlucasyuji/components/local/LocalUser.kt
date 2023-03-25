package br.com.devlucasyuji.components.local

import androidx.compose.runtime.compositionLocalOf
import br.com.devlucasyuji.domain.model.User

val LocalUser = compositionLocalOf { User.default }