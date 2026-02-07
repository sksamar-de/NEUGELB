package com.example.details_data.network

import com.example.details_data.model.DetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailApi {
    @GET("/3/movie/{id}")
    suspend fun getDetail(@Path("id") id: Int): DetailResponse
}