package com.ujizin.leafy.components.local

import androidx.compose.runtime.compositionLocalOf
import com.ujizin.leafy.domain.model.User

val LocalUser = compositionLocalOf { User.default }