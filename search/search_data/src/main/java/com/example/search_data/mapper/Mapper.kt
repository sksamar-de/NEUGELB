package com.example.search_data.mapper

import com.example.search_data.model.MovieDto
import com.example.search_data.model.MoviesResponse
import com.example.search_domain.model.Movie

fun MoviesResponse.toMovies(): List<Movie?> {
    return results?.map { it?.toMovie() } ?: emptyList()
}

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        release = release_date?.split("-")?.firstOrNull()?: release_date
    )
}