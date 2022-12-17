package br.com.devlucasyuji.publish

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import br.com.devlucasyuji.navigation.Args
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.publishGraph() {
    composable(
        destination = Destination.Publish,
        arguments = listOf(navArgument(Args.ImageFilePath) { type = NavType.StringType })
    ) {
        PublishSection()
    }
}
