package com.example.movies_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    private var _currentState by mutableStateOf(MoviesState())
    val currentState: MoviesState
        get() = _currentState
    private val _movies: SnapshotStateList<Movie?> = SnapshotStateList()
    val movies: List<Movie?>
        get() = _movies

    init {
        getAllMovies()
    }

    fun getNextMovies() {
        useCase.invoke(page = currentState.currentPage + 1).onEach {
            when (it) {
                Resource.Loading -> {
                    _currentState = MoviesState(isLoadingMore = true)
                }

                is Resource.Success<Movies> -> {
                    _movies.addAll(it.result.results)
                    _currentState = MoviesState(
                        currentPage = it.result.page?.toInt() ?: 0,
                        totalPage = it.result.total_pages?.toInt() ?: 0,
                    )
                }

                is Resource.Error<*> -> {
                    _currentState = MoviesState(isLoadingMore = false, error = it.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAllMovies() {
        useCase.invoke(page = 1).onEach {
            when (it) {
                Resource.Loading -> {
                    _currentState = MoviesState(isLoading = true)
                }

                is Resource.Success<Movies> -> {
                    _movies.clear()
                    _movies.addAll(it.result.results)
                    _currentState = MoviesState(
                        currentPage = it.result.page?.toInt() ?: 0,
                        totalPage = it.result.total_pages?.toInt() ?: 0,
                    )
                }

                is Resource.Error<*> -> {
                    _currentState = MoviesState(isLoading = false, error = it.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}