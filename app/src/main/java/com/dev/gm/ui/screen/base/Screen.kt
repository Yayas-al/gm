package com.dev.gm.ui.screen.base

sealed class Screen(
    val route: String
){

    object HomeScreen : Screen(route = "Home")
    object GameDetailScreen : Screen(route = "gameDetail/{id}")
    object SearchScreen: Screen(
        route = "search?mode={mode}&filter={filter}"
    )
}