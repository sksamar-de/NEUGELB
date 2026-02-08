package com.example.movies_domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movies_domain.model.Movie
import com.example.movies_domain.pagination.MoviesPagingSource
import com.example.movies_domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(private val repository: MoviesRepository) {

    operator fun invoke(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = {
                MoviesPagingSource(repository)
            }
        ).flow
    }
}