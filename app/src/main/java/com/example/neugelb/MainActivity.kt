package com.example.neugelb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.common_utls.LocalNavHostController
import com.example.common_utls.NavigationRoute
import com.example.movies_presentation.MoviesScreen
import com.example.neugelb.root.Root
import com.example.search_presentation.SearchScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Root {
                Screens()
            }
        }
    }

    @Composable
    fun Screens() {
        val navHostController = LocalNavHostController.current
        NavHost(
            navController = navHostController,
            startDestination = NavigationRoute.MoviesScreen.route
        ) {
            composable(route = NavigationRoute.MoviesScreen.route) {
                MoviesScreen()
            }
            composable(route = NavigationRoute.SearchScreen.route) {
                SearchScreen()
            }
        }
    }
}