package com.example.movies_domain.use_case

import com.example.common_utls.Resource
import com.example.movies_domain.model.Movies
import com.example.movies_domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetMoviesUseCase(private val repository: MoviesRepository) {

    operator fun invoke(page: Int): Flow<Resource<Movies>> = flow {
        emit(Resource.Loading)
        try {
            val result = repository.getMovies(page = page)
            emit(Resource.Success(result))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message ?: "Some Error"))
        } catch (e: Exception){
            emit(Resource.Error(message = e.message ?: "Some Error"))
        }
    }

}