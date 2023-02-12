package br.com.devlucasyuji.camerareminder

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import br.com.devlucasyuji.alarm.alarmGraph
import br.com.devlucasyuji.alarms.alarmsGraph
import br.com.devlucasyuji.camera.cameraGraph
import br.com.devlucasyuji.components.ui.Scaffold
import br.com.devlucasyuji.components.ui.navigation.navigationEnterTransition
import br.com.devlucasyuji.components.ui.navigation.navigationExitTransition
import br.com.devlucasyuji.home.homeGraph
import br.com.devlucasyuji.navigation.Args
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.navigate
import br.com.devlucasyuji.publish.publishGraph
import br.com.devlucasyuji.search.searchGraph
import br.com.devlucasyuji.settings.settingsGraph
import br.com.devlucasyuji.themes.CameraReminderTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            CameraReminderTheme(dynamicColor = true) {
                val navController = rememberAnimatedNavController()
                Scaffold(navController = navController) { CameraNavigation(navController) }
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun CameraNavigation(navController: NavHostController) {
        AnimatedNavHost(
            navController = navController,
            startDestination = Destination.Home.route,
        ) {
            homeGraph(
                enterTransition = { navigationEnterTransition(navController) },
                exitTransition = { navigationExitTransition(navController) },
                onTakePictureClick = { navController.navigate(Destination.Camera) }
            )
            searchGraph(
                enterTransition = { navigationEnterTransition(navController) },
                exitTransition = { navigationExitTransition(navController) }
            )
            alarmsGraph(
                enterTransition = { navigationEnterTransition(navController) },
                exitTransition = { navigationExitTransition(navController) }
            )
            alarmGraph(
                onBackPressed = { navController.navigateUp() },
                enterTransition = { slideIntoContainer(AnimatedContentScope.SlideDirection.Start) },
                exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.End) }
            )
            cameraGraph(
                onBackPressed = { navController.navigateUp() },
                onSaveClicked = { imagePath ->
                    navController.navigate(
                        Destination.Publish.withArguments(
                            Args.ImageFilePath to Uri.encode(imagePath)
                        )
                    )
                }
            )
            settingsGraph(
                enterTransition = { navigationEnterTransition(navController) },
                exitTransition = { navigationExitTransition(navController) }
            )
            publishGraph(
                onBackPressed = { navController.navigateUp() },
                onNextClick = { navController.navigate(Destination.Alarm) }
            )
        }
    }
}
