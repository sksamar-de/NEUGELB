package com.example.common_utls

import androidx.compose.runtime.Composable

interface Navigator {
    fun navigate(route: String)
    interface Provider {
        fun getRoutes(routes: Routes): Navigator
    }
}