package com.ujizin.leafy

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
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.ujizin.leafy.about.aboutGraph
import com.ujizin.leafy.alarm.alarm.alarmGraph
import com.ujizin.leafy.alarm.alarms.alarmsGraph
import com.ujizin.leafy.camera.cameraGraph
import com.ujizin.leafy.core.navigation.Args
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.navigation.navigate
import com.ujizin.leafy.core.navigation.navigateUp
import com.ujizin.leafy.core.ui.components.Scaffold
import com.ujizin.leafy.core.ui.components.navigation.navigationEnterTransition
import com.ujizin.leafy.core.ui.components.navigation.navigationExitTransition
import com.ujizin.leafy.home.homeGraph
import com.ujizin.leafy.preferences.preferencesGraph
import com.ujizin.leafy.publish.publishGraph
import com.ujizin.leafy.search.searchGraph
import com.ujizin.leafy.settings.settingsGraph
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LeafyNavigation(
    navController: NavHostController,
    drawerState: DrawerState,
    onBackPressed: () -> Unit
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
                        Destination.Search.withArguments(Args.SearchAutoFocus to true),
                    )
                },
                onDrawerClick = { scope.launch { drawerState.open() } },
            )
            searchGraph(
                enterTransition = { navigationEnterTransition(navController) },
                exitTransition = { navigationExitTransition(navController) },
                onDrawerClick = { scope.launch { drawerState.open() } },
                onTakePictureClick = { navController.navigate(Destination.Camera) },
                onScroll = { showBottomNavigation = it },
            )
            alarmsGraph(
                enterTransition = { navigationEnterTransition(navController) },
                exitTransition = { navigationExitTransition(navController) },
            )
            alarmGraph(
                onBackPressed = navController::navigateUp,
                onSaved = { navController.navigateUp(times = 3) },
                enterTransition = { slideIntoContainer(AnimatedContentScope.SlideDirection.Start) },
                exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.Start) },
            )
            cameraGraph(
                onBackPressed = navController::navigateUp,
                onSaveClicked = { navController.navigate(Destination.Publish) },
            )
            settingsGraph(
                enterTransition = { navigationEnterTransition(navController) },
                exitTransition = { navigationExitTransition(navController) },
            )
            publishGraph(
                onBackPressed = navController::navigateUp,
                onNextClick = { navController.navigate(Destination.Alarm) },
                exitTransition = { slideOutOfContainer(AnimatedContentScope.SlideDirection.Start) },
            )
            aboutGraph(onBackPressed = navController::navigateUp)
            preferencesGraph(onBackPressed = navController::navigateUp)
        }
    }
}