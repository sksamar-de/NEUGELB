package com.example.neugelb.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.example.common_utls.LocalNavHostController
import com.example.neugelb.ui.theme.NEUGELBTheme

@Composable
fun Root(content: @Composable () -> Unit) {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavHostController provides navController) {
        NEUGELBTheme(
            content = content
        )
    }
}