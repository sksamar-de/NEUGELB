package com.example.movies_domain.model
import java.util.UUID


data class Movies(
    val page: Long?,
    val results: List<Movie?>,
    val total_pages: Long?,
    val total_results: Long?
)
data class Movie(
    val uniqueKey: Long = UUID.randomUUID().mostSignificantBits and Long.MAX_VALUE,
    val id: Long?,
    val original_language: String?,
    val poster_path: String?,
    val backdrop_path: String?,
    val release_date: String?,
    val title: String?,
    val overview: String?,
    val vote_average: Double?,
    val popularity: Double?,
)