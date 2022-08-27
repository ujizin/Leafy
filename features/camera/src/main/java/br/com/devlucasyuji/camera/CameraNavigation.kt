package br.com.devlucasyuji.camera

import androidx.navigation.NavGraphBuilder
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.composable

fun NavGraphBuilder.cameraGraph() {
    composable(Destination.Camera) { CameraRoute() }
}
