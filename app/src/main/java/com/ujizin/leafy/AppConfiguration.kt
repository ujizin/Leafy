package com.ujizin.leafy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import com.ujizin.leafy.core.themes.LeafyTheme
import com.ujizin.leafy.core.ui.extensions.Content
import com.ujizin.leafy.core.ui.local.LocalUser
import com.ujizin.leafy.user.setLanguage

@Composable
fun AppConfiguration(
    content: @Composable Content
) {
    val user by rememberUser()
    val darkTheme = user.isUserInDarkTheme()
    val language by rememberUpdatedState(user.settings.language)
    val dynamicColor by rememberUpdatedState(user.settings.dynamicColor)
    val context = LocalContext.current

    LaunchedEffect(language) { context.setLanguage(language) }

    CompositionLocalProvider(LocalUser provides user) {
        LeafyTheme(
            darkTheme = darkTheme,
            dynamicColor = dynamicColor,
            content = content
        )
    }
}
