package com.example.movies_data.mapper

import com.example.movies_data.model.MovieDto
import com.example.movies_data.model.MoviesResponse
import com.example.movies_domain.model.Movie
import com.example.movies_domain.model.Movies

fun MoviesResponse.toMovies(): Movies {
    return Movies(
        page = page,
        total_pages = total_pages,
        total_results = total_results,
        results = results.map { it.toMovie() }
    )
}

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        original_language = original_language,
        title = title,
        poster_path = poster_path,
        release_date = release_date,
        vote_average = vote_average,
        backdrop_path = backdrop_path
    )
}