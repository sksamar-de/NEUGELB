package com.example.neugelb.navigation

import androidx.compose.foundation.layout.Column
import com.example.common_utls.Navigator
import com.example.common_utls.Routes

class DefaultNavigation : Navigator.Provider {
    override fun getRoutes(routes: Routes): Navigator {
        return when(routes){
            Composables.MoviesComposable -> {
                return Column()
            }
            Composables.SearchComposable -> {
                return Column()
            }
        }
    }
}