package com.example.movies_domain.model


data class Movies(
    val page: Int?,
    val results: List<Movie?>,
    val total_pages: Int?,
    val total_results: Int?
)
data class Movie(
    val id: Int?,
    val original_language: String?,
    val poster_path: String?,
    val backdrop_path: String?,
    val release_date: String?,
    val title: String?,
    val overview: String?,
    val vote_average: Double?,
)
