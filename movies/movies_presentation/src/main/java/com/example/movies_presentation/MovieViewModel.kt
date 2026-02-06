package com.example.movies_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.MutableSnapshot
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_utls.Resource
import com.example.movies_domain.model.Movie
import com.example.movies_domain.model.Movies
import com.example.movies_domain.use_case.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val useCase: GetMoviesUseCase) : ViewModel() {
    var currentState by mutableStateOf(MoviesState())
    val movies: SnapshotStateList<Movie> = SnapshotStateList()

    init {
        getAllMovies()
    }
    private fun getAllMovies() {
        useCase.invoke(page = 1).onEach {
            when(it){
                Resource.Loading -> {
                    currentState = MoviesState(isLoading = true)
                }
                is Resource.Success<Movies> -> {
                    movies.addAll(it.result.results)
                    currentState = MoviesState(currentPage = it.result.page, totalPage = it.result.total_pages)
                }
                is Resource.Error<*> -> {
                    currentState = MoviesState(error = it.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}
