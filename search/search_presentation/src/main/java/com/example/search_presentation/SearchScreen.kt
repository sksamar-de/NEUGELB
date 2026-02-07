package com.example.search_presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.common_utls.Assent
import com.example.common_utls.LocalNavHostController
import com.example.search_presentation.component.AutoCompleteMovie
import com.example.search_presentation.component.SearchField
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val navHostController = LocalNavHostController.current
    val state = viewModel.state.collectAsState()
    LaunchedEffect(viewModel.search) {
        delay(500L)
        if (viewModel.search.isEmpty()) viewModel.reset()
        if (viewModel.search.isNotEmpty()) {
            viewModel.searchMovies()
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                        text = buildAnnotatedString {
                            append(stringResource(R.string.search))
                            withStyle(SpanStyle(color = Assent)) {
                                append(stringResource(R.string.dot))
                            }
                        }
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navHostController.popBackStack()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                            contentDescription = stringResource(R.string.nav_back_icon)
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            SearchField(
                value = viewModel.search,
                onValueChange = {
                    viewModel.search = it
                }
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                if (state.value.isLoading) CircularProgressIndicator()
                if (state.value.error.isNotEmpty()) Text(
                    text = state.value.error,
                    color = MaterialTheme.colorScheme.error
                )
                if (viewModel.search.isEmpty()) Text(
                    text = stringResource(R.string.start_looking_for_your_favourite_movie),
                    color = MaterialTheme.colorScheme.primary
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.value.searchList) {
                        AutoCompleteMovie(movie = it)
                    }
                }
            }
        }
    }
}

