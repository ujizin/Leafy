package br.com.devlucasyuji.camerareminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.devlucasyuji.alarm.alarmGraph
import br.com.devlucasyuji.camera.cameraGraph
import br.com.devlucasyuji.components.ui.Scaffold
import br.com.devlucasyuji.home.homeGraph
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.search.searchGraph
import br.com.devlucasyuji.settings.settingsGraph
import br.com.devlucasyuji.themes.CameraReminderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            CameraReminderTheme(dynamicColor = true) {
                with(rememberNavController()) {
                    Scaffold { CameraNavigation(this@with) }
                }
            }
        }
    }

    @Composable
    fun CameraNavigation(navController: NavHostController) {
        NavHost(navController, Destination.Home.route) {
            with(navController) {
                homeGraph()
                searchGraph()
                alarmGraph()
                cameraGraph()
                settingsGraph()
            }
        }
    }
}