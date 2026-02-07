package com.example.movies_data.mapper

import com.example.movies_data.model.MovieDto
import com.example.movies_data.model.MoviesResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class MappersTest {
    @Test
    fun `toMovie maps MovieDto to Movie`() {
        val movieDto = MovieDto(
            adult = false,
            backdrop_path = "/backdrop",
            genre_ids = listOf(1),
            id = 1L,
            original_language = "en",
            original_title = "Title",
            overview = "Overview",
            popularity = 1.0,
            poster_path = "/poster",
            release_date = "2021-01-01",
            title = "Title",
            video = false,
            vote_average = 8.0,
            vote_count = 100
        )
        val movie = movieDto.toMovie()
        assertEquals(1L, movie.id)
        assertEquals("en", movie.original_language)
        assertEquals("Title", movie.title)
        assertEquals("Overview", movie.overview)
        assertEquals("/poster", movie.poster_path)
        assertEquals("2021-01-01", movie.release_date)
        assertEquals(8.0, movie.vote_average)
        assertEquals("/backdrop", movie.backdrop_path)
        assertEquals(1.0, movie.popularity)
    }

    @Test
    fun `toMovies maps MoviesResponse to Movies`() {
        val response = MoviesResponse(
            page = 1,
            results = listOf(
                MovieDto(null, null, null, 1L, null, null, null, null, null, null, "Movie 1", null, null, null)
            ),
            total_pages = 1,
            total_results = 1
        )
        val movies = response.toMovies()
        assertEquals(1L, movies.page)
        assertEquals(1, movies.results.size)
        assertEquals(1L, movies.results[0]?.id)
        assertEquals("Movie 1", movies.results[0]?.title)
    }
}