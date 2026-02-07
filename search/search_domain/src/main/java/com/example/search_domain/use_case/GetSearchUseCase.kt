package com.example.search_domain.use_case

import com.example.common_utls.Resource
import com.example.search_domain.model.Movie
import com.example.search_domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSearchUseCase(private val repository: SearchRepository) {

    fun invoke(query: String): Flow<Resource<List<Movie?>>> = flow {
        emit(Resource.Loading)
        try {
            val response = repository.searchMovie(query = query)
            emit(Resource.Success(result = response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Some Error"))
        }
    }

}