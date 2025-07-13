package com.ujizin.leafy

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ujizin.leafy.about.aboutGraph
import com.ujizin.leafy.alarm.ui.alarmGraph
import com.ujizin.leafy.camera.cameraGraph
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.navigation.navigateUp
import com.ujizin.leafy.core.ui.components.ScaffoldWithDrawer
import com.ujizin.leafy.core.ui.components.navigation.bottombar.navigateToItem
import com.ujizin.leafy.features.plant.plantGraph
import com.ujizin.leafy.features.tasks.tasksGraph
import com.ujizin.leafy.home.homeGraph
import com.ujizin.leafy.preferences.preferencesGraph
import com.ujizin.leafy.publish.publishGraph
import com.ujizin.leafy.search.searchGraph
import kotlinx.coroutines.launch

@Composable
fun LeafyNavigation(
    navController: NavHostController,
    drawerState: DrawerState,
) {
    val scope = rememberCoroutineScope()
    ScaffoldWithDrawer(
        modifier = Modifier
            .imePadding()
            .navigationBarsPadding(),
        drawerState = drawerState,
        navController = navController,
    ) {
        NavHost(
            navController = navController,
            startDestination = Destination.Home,
        ) {
            homeGraph(
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                onTakePictureClick = { navController.navigate(Destination.Camera) },
                onSearchClick = {
                    navController.navigateToItem(
                        destination = Destination.Search(true),
                        restoreState = false,
                    )
                },
                onDrawerClick = { scope.launch { drawerState.open() } },
                onPlantClick = { plantId ->
                    navController.navigate(Destination.PlantDetails(plantId))
                },
            )
            searchGraph(
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                onDrawerClick = { scope.launch { drawerState.open() } },
                onTakePictureClick = { navController.navigate(Destination.Camera) },
                onPlantClick = { plantId ->
                    navController.navigate(Destination.PlantDetails(plantId))
                },
            )
            tasksGraph(
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                onPlantClick = { plantId ->
                    navController.navigate(Destination.PlantDetails(plantId))
                },
                onTakePictureClick = { navController.navigate(Destination.Camera) },
                onDrawerClick = { scope.launch { drawerState.open() } },
            )
            preferencesGraph(
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
            )
            alarmGraph(
                onBackPressed = navController::navigateUp,
                onSaved = { navController.navigateUp(times = 3) },
                enterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start)
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start)
                },
            )
            cameraGraph(
                onBackPressed = navController::navigateUp,
                onSaveClicked = { navController.navigate(Destination.Publish) },
            )
            publishGraph(
                onBackPressed = navController::navigateUp,
                onNextClick = { navController.navigate(Destination.Alarm) },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start)
                },
            )
            aboutGraph(onBackPressed = navController::navigateUp)
            plantGraph(onBackPressed = navController::navigateUp)
        }
    }
}
