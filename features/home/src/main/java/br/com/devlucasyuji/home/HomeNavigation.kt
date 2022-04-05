package br.com.devlucasyuji.home

import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.NavGraph
import br.com.devlucasyuji.navigation.Navigation
import br.com.devlucasyuji.navigation.composable

object HomeNavigation : Navigation {

    override fun navGraph(): NavGraph = {
        composable(Destination.Home) { HomeSection() }
    }
}
