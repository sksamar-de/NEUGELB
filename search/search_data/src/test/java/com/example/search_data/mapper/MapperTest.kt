package com.example.search_data.mapper

import com.example.search_data.model.MovieDto
import com.example.search_data.model.MoviesResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperTest {
    @Test
    fun `toMovie maps MovieDto to Movie`() {
        val movieDto = MovieDto(
            adult = false,
            backdrop_path = "/backdrop.jpg",
            genre_ids = listOf(1),
            id = 123L,
            original_language = "en",
            original_title = "Original Title",
            overview = "Overview",
            popularity = 10.0,
            poster_path = "/poster.jpg",
            release_date = "2023-10-27",
            title = "Test Movie",
            video = false,
            vote_average = 7.5,
            vote_count = 100
        )
        val movie = movieDto.toMovie()
        assertEquals(123L, movie.id)
        assertEquals("Test Movie", movie.title)
        assertEquals("2023", movie.release)
    }

    @Test
    fun `toMovie handles null or malformed release_date`() {
        val movieDto = MovieDto(
            adult = null, backdrop_path = null, genre_ids = null, id = 1L,
            original_language = null, original_title = null, overview = null,
            popularity = null, poster_path = null, release_date = null,
            title = "Title", video = null, vote_average = null, vote_count = null
        )
        val movie = movieDto.toMovie()
        assertEquals(null, movie.release)
        val movieDtoMalformed = movieDto.copy(release_date = "InvalidDate")
        val movieMalformed = movieDtoMalformed.toMovie()
        assertEquals("InvalidDate", movieMalformed.release)
    }

    @Test
    fun `toMovies maps MoviesResponse to List of Movie`() {
        val response = MoviesResponse(
            page = 1,
            results = listOf(
                MovieDto(null, null, null, 1L, null, null, null, null, null, "2021-01-01", "Movie 1", null, null, null),
                MovieDto(null, null, null, 2L, null, null, null, null, null, "2022-05-05", "Movie 2", null, null, null)
            ),
            total_pages = 1,
            total_results = 2
        )
        val movies = response.toMovies()
        assertEquals(2, movies.size)
        assertEquals("2021", movies[0]?.release)
        assertEquals("2022", movies[1]?.release)
    }
}