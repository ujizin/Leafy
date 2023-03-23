package br.com.devlucasyuji.camerareminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import br.com.devlucasyuji.alarm.alarm.alarmGraph
import br.com.devlucasyuji.alarm.alarms.alarmsGraph
import br.com.devlucasyuji.camera.cameraGraph
import br.com.devlucasyuji.components.ui.Scaffold
import br.com.devlucasyuji.components.ui.navigation.navigationEnterTransition
import br.com.devlucasyuji.components.ui.navigation.navigationExitTransition
import br.com.devlucasyuji.home.homeGraph
import br.com.devlucasyuji.navigation.Args
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.navigate
import br.com.devlucasyuji.navigation.navigateUp
import br.com.devlucasyuji.publish.publishGraph
import br.com.devlucasyuji.search.searchGraph
import br.com.devlucasyuji.settings.settingsGraph
import br.com.devlucasyuji.themes.CameraReminderTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ujizin.about.aboutGraph
import com.ujizin.preferences.preferencesGraph
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        setContent {
            CameraReminderTheme(dynamicColor = true) {
                val navController = rememberAnimatedNavController()
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                var showBottomNavigation by remember { mutableStateOf(true) }

                Scaffold(
                    modifier = Modifier
                        .imePadding()
                        .navigationBarsPadding(),
                    drawerState = drawerState,
                    navController = navController,
                    showBottomNavigation = showBottomNavigation,
                ) {
                    CameraNavigation(
                        navController = navController,
                        drawerState = drawerState,
                        onNavigationChanged = { showBottomNavigation = it }
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
    @Composable
    fun CameraNavigation(
        navController: NavHostController,
        drawerState: DrawerState,
        onNavigationChanged: (Boolean) -> Unit
    ) {
        val scope = rememberCoroutineScope()

        BackHandler { scope.launch { navController.navigateUp(drawerState) } }

        AnimatedNavHost(
            navController = navController,
            startDestination = Destination.Home.route,
        ) {
            homeGraph(
                enterTransition = { navigationEnterTransition(navController) },
                exitTransition = { navigationExitTransition(navController) },
                onTakePictureClick = { navController.navigate(Destination.Camera) },
                onSearchClick = {
                    navController.navigate(
                        Destination.Search.withArguments(Args.SearchAutoFocus to true)
                    )
                },
                onDrawerClick = { scope.launch { drawerState.open() } },
            )
            searchGraph(
                enterTransition = { navigationEnterTransition(navController) },
                exitTransition = { navigationExitTransition(navController) },
                onDrawerClick = { scope.launch { drawerState.open() } },
                onTakePictureClick = { navController.navigate(Destination.Camera) },
                onScroll = onNavigationChanged
            )
            alarmsGraph(
                enterTransition = { navigationEnterTransition(navController) },
                exitTransition = { navigationExitTransition(navController) }
            )
            alarmGraph(
                onBackPressed = navController::navigateUp,
                onSaved = { navController.navigateUp(times = 3) },
                enterTransition = { slideIntoContainer(AnimatedContentScope.SlideDirection.Start) },
                exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.Start) }
            )
            cameraGraph(
                onBackPressed = navController::navigateUp,
                onSaveClicked = { navController.navigate(Destination.Publish) }
            )
            settingsGraph(
                enterTransition = { navigationEnterTransition(navController) },
                exitTransition = { navigationExitTransition(navController) }
            )
            publishGraph(
                onBackPressed = navController::navigateUp,
                onNextClick = { navController.navigate(Destination.Alarm) },
                exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.Start) }
            )
            aboutGraph(onBackPressed = navController::navigateUp)
            preferencesGraph(onBackPressed = navController::navigateUp)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    suspend fun NavController.navigateUp(drawerState: DrawerState) {
        if (drawerState.isOpen) {
            drawerState.close()
            return
        }

        if (!navigateUp()) finish()
    }
}
