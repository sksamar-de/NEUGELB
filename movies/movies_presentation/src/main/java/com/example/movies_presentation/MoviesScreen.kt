package com.example.movies_presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.movies_presentation.component.Header
import com.example.movies_presentation.component.LoadingMore
import com.example.movies_presentation.component.MovieContainer

@Composable
fun MoviesScreen(
    viewModel: MovieViewModel = hiltViewModel()
) {
    val movies = viewModel.movies.collectAsLazyPagingItems()
    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                item {
                    Header()
                }

                items(
                    count = movies.itemCount,
                    key = movies.itemKey { it.uniqueKey },
                    contentType = movies.itemContentType { "movie" }
                ) { index ->
                    movies[index]?.let { movie ->
                        MovieContainer(movie = movie)
                    }
                }

                item {
                    if (movies.loadState.append is LoadState.Loading) LoadingMore()
                }
            }

            if (movies.loadState.refresh is LoadState.Loading) CircularProgressIndicator()

            val errorState = movies.loadState.append as? LoadState.Error ?: movies.loadState.refresh as? LoadState.Error

            errorState?.let {
                Text(
                    text = it.error.localizedMessage ?: "Some Error",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}