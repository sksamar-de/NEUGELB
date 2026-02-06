package com.example.movies_domain.repository

import com.example.movies_domain.model.Movies

interface MoviesRepository {
    suspend fun getMovies(page: Int): Movies
}