package com.example.common_utls

sealed class Routes {
    object MoviesRoute: Routes()
    object SearchRoute: Routes()
}