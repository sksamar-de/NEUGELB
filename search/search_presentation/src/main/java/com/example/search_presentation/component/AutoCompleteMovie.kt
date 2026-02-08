package com.example.search_presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.common_utls.LocalNavHostController
import com.example.common_utls.NavigationRoute
import com.example.search_domain.model.Movie

@Composable
fun AutoCompleteMovie(
    movie: Movie?
) {
    val navController = LocalNavHostController.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    route = NavigationRoute.DetailScreen.route.replace(
                        "{id}",
                        "${movie?.id}"
                    )
                )
            }.testTag("movie_name")
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 18.dp),
            text = "${movie?.title} (${movie?.release})",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}