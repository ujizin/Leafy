package br.com.devlucasyuji.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeGraph(onTakePictureClick: OnClick) {
    composable(Destination.Home) { HomeRoute(onTakePictureClick = onTakePictureClick) }
}
