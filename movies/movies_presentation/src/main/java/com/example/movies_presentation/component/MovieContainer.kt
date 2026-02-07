package com.example.movies_presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.common_utls.Constants
import com.example.common_utls.LocalNavHostController
import com.example.common_utls.NavigationRoute
import com.example.movies_domain.model.Movie
import com.example.movies_presentation.R
import java.text.DecimalFormat

@Composable
fun MovieContainer(
    movie: Movie?
) {
    val navHostController = LocalNavHostController.current
    Row(
        modifier = Modifier
            .height(height = 273.dp)
            .fillMaxWidth()
            .padding(12.dp)
            .clickable {
                navHostController.navigate(
                    route = NavigationRoute.DetailScreen.route.replace(
                        "{id}",
                        "${movie?.id}"
                    )
                )
            }
    ) {
        Card(
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 4.dp)
                .weight(1f)
        ) {
            movie?.poster_path?.let {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = Constants.POSTER_PATH + movie.poster_path,
                    contentDescription = movie.title,
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(start = 4.dp)
                .weight(1f)
        ) {
            Text(
                text = movie?.title ?: "N/A",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.rating),
                    contentDescription = stringResource(R.string.rating),
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = DecimalFormat("#.##").format(movie?.popularity) ?: "N/A",
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 22.sp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie?.overview ?: "N/A",
                style = MaterialTheme.typography.titleSmall.copy(fontSize = 13.sp),
                color = MaterialTheme.colorScheme.onSurface.copy(.55f),
                maxLines = 6,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}