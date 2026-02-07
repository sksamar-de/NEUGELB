package com.example.search_data.model

data class MoviesResponse(
    val page: Long?,
    val results: List<MovieDto?>?,
    val total_pages: Long?,
    val total_results: Long?
)