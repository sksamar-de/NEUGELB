package com.example.common_utls

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

sealed class NavigationRoute(val route: String, val name: String? = null, val id: Int? = null) {
    object MoviesScreen : NavigationRoute(route = "movies_screen")
    object SearchScreen : NavigationRoute(route = "search_screen")
}

val LocalNavHostController = staticCompositionLocalOf<NavHostController> { error("NavHostController not provided") }