package com.ujizin.leafy

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import com.ujizin.leafy.core.themes.LeafyTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ujizin.leafy.core.ui.local.LocalUser
import com.ujizin.leafy.domain.model.Language
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        setupContent()
    }

    @OptIn(ExperimentalAnimationApi::class)
    private fun setupContent() {
        setContent {
            val user by rememberUser()
            val darkTheme = user.isUserInDarkTheme()
            val language by rememberUpdatedState(user.settings.language)
            val dynamicColor by rememberUpdatedState(user.settings.dynamicColor)

            LaunchedEffect(language) { setUserLanguage(language) }

            CompositionLocalProvider(LocalUser provides user) {
                LeafyTheme(
                    darkTheme = darkTheme,
                    dynamicColor = dynamicColor,
                ) {
                    val navController = rememberAnimatedNavController()
                    val drawerState = rememberDrawerState(DrawerValue.Closed)
                    val scope = rememberCoroutineScope()

                    LeafyNavigation(
                        navController = navController,
                        drawerState = drawerState,
                        onBackPressed = { scope.launch { navController.navigateUp(drawerState) } }
                    )
                }
            }
        }
    }

    private fun setUserLanguage(language: Language) {
        val config = resources.configuration
        Locale.setDefault(language.locale)
        config.setLocale(language.locale)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            createConfigurationContext(config)
        }

        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private suspend fun NavController.navigateUp(drawerState: DrawerState) {
        if (drawerState.isOpen) {
            drawerState.close()
            return
        }

        if (!navigateUp()) finish()
    }
}
