package com.example.search_data.network

import com.example.search_data.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI {
    @GET("/3/search/movie")
    suspend fun searchMovie(@Query("query") query: String): MoviesResponse
}