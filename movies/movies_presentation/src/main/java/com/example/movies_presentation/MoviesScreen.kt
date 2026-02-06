package com.example.movies_presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.movies_presentation.component.isScrolledToTheEnd

@Composable
fun MoviesScreen(
    viewModel: MovieViewModel = hiltViewModel()
) {
    val state = viewModel.currentState
    val listState = rememberLazyListState()

    val reachedBottom by remember {
        derivedStateOf {
            listState.isScrolledToTheEnd()
        }
    }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom && !state.isLoading && viewModel.movies.isNotEmpty()) {
            viewModel.getNextMovies()
        }
    }

    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) CircularProgressIndicator()
            if (state.error.trim().isNotEmpty()) Text(state.error)


            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize()
            ) {
                items(viewModel.movies) {
                    Text(modifier = Modifier
                        .fillMaxWidth()
                        .size(80.dp), text = it?.title?:"N/A")
                }
            }
        }
    }
}
