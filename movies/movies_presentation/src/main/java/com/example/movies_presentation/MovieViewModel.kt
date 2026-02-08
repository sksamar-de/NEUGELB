package com.example.movies_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movies_domain.model.Movie
import com.example.movies_domain.use_case.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(useCase: GetMoviesUseCase) : ViewModel() {
    val movies: Flow<PagingData<Movie>> = useCase.invoke().cachedIn(viewModelScope)
}