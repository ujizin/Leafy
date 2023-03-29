package br.com.devlucasyuji.camerareminder

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.ujizin.about.aboutGraph
import com.ujizin.preferences.preferencesGraph
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LeafyNavigation(
    navController: NavHostController,
    drawerState: DrawerState,
    onBackPressed: () -> Unit,
) {
    val scope = rememberCoroutineScope()

    var showBottomNavigation by remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier
            .imePadding()
            .navigationBarsPadding(),
        drawerState = drawerState,
        navController = navController,
        showBottomNavigation = showBottomNavigation,
    ) {
        BackHandler(onBack = onBackPressed)

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
                onScroll = { showBottomNavigation = it }
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
}