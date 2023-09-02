package com.ujizin.leafy

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ujizin.leafy.alarm.AlarmService
import com.ujizin.leafy.alarm.extensions.plantId
import com.ujizin.leafy.core.navigation.Args
import com.ujizin.leafy.core.navigation.Destination
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        setupContent()
    }

    @OptIn(ExperimentalAnimationApi::class)
    private fun setupContent() {
        setContent {
            AppConfiguration {
                val navController = rememberAnimatedNavController()
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                LaunchPlantDetail(navController)

                LeafyNavigation(
                    navController = navController,
                    drawerState = drawerState,
                    onBackPressed = { scope.launch { navController.navigateUp(drawerState) } },
                )
            }
        }
    }

    @Composable
    private fun LaunchPlantDetail(navController: NavHostController) {
        LaunchedEffect(intent.plantId) {
            if (intent.plantId != -1L) {
                if (intent.action == AlarmService.STOP_ACTION) {
                    stopService(Intent(this@MainActivity, AlarmService::class.java))
                }

                val plantDetailDestination = Destination.PlantDetails.withArguments(
                    Args.PlantId to intent.plantId
                )
                navController.navigate(plantDetailDestination)
            }
        }
    }

    private suspend fun NavController.navigateUp(drawerState: DrawerState) {
        if (drawerState.isOpen) {
            drawerState.close()
            return
        }

        if (!navigateUp()) finish()
    }
}
