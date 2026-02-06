package com.example.movies_data.repository

import com.example.movies_data.mapper.toMovies
import com.example.movies_data.network.MoviesApi
import com.example.movies_domain.model.Movies
import com.example.movies_domain.repository.MoviesRepository

class MoviesRepositoryImpl(private val api: MoviesApi) : MoviesRepository {

    override suspend fun getMovies(page: Int): Movies {
        return api.getMovies(page = page).toMovies()
    }
}