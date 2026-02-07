package com.example.search_domain.repository

import com.example.search_domain.model.Movie

interface SearchRepository {

    suspend fun searchMovie(query: String): List<Movie?>
}