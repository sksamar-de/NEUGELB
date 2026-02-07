package com.example.search_data.repository

import com.example.search_data.mapper.toMovies
import com.example.search_data.network.SearchAPI
import com.example.search_domain.model.Movie
import com.example.search_domain.repository.SearchRepository

class SearchRepositoryImpl(private val api: SearchAPI): SearchRepository {
    override suspend fun searchMovie(query: String): List<Movie?> {
        return api.searchMovie(query = query).toMovies()
    }

}