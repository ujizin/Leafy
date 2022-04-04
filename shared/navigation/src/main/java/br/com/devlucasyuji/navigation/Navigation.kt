package br.com.devlucasyuji.navigation

import androidx.navigation.NavGraphBuilder

typealias NavGraph = NavGraphBuilder.() -> Unit

internal val Navigators: MutableList<NavGraph> = mutableListOf()

fun addNavigators(vararg navigation: Navigation) {
    Navigators.addAll(navigation.toList().map { it.navGraph() })
}

interface Navigation {
    fun navGraph(): NavGraph
}