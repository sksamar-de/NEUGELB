package com.example.movies_data.network

import com.example.movies_data.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("discover/movie")
    suspend fun getMovies(@Query("page") page: Int): MoviesResponse
}