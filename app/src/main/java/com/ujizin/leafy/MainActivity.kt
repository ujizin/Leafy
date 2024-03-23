package com.ujizin.leafy

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ujizin.leafy.alarm.AlarmService
import com.ujizin.leafy.core.navigation.Args
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.navigation.navigate
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

    private fun setupContent() {
        setContent {
            AppConfiguration {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                LaunchPlantDetail(navController)

                BackHandler(navController, drawerState)

                LeafyNavigation(
                    navController = navController,
                    drawerState = drawerState,
                )
            }
        }
    }

    @Composable
    private fun BackHandler(
        navController: NavHostController,
        drawerState: DrawerState,
    ) {
        val scope = rememberCoroutineScope()
        val isDrawerOpen by rememberUpdatedState(drawerState.isOpen)

        when {
            isDrawerOpen -> BackHandler { scope.launch { drawerState.close() } }
            else -> BackHandler { if (!navController.navigateUp()) finish() }
        }
    }

    @Composable
    private fun LaunchPlantDetail(navController: NavHostController) {
        val plantId = remember { intent.getLongExtra(AlarmService.PLANT_ID, -1) }
        LaunchedEffect(plantId) {
            if (plantId != -1L) {
                if (intent.action == AlarmService.STOP_ACTION) {
                    stopService(Intent(this@MainActivity, AlarmService::class.java))
                }

                navController.navigate(Destination.PlantDetails, Args.PlantId to plantId)
            }
        }
    }
}
