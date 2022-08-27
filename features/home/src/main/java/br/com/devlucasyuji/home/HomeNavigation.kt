package br.com.devlucasyuji.home

import androidx.navigation.NavGraphBuilder
import br.com.devlucasyuji.components.extensions.OnClick
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.composable

fun NavGraphBuilder.homeGraph(onTakePictureClick: OnClick) {
    composable(Destination.Home) { HomeRoute(onTakePictureClick = onTakePictureClick) }
}
