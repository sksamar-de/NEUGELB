package com.example.search_data.repository

import com.example.search_data.model.MovieDto
import com.example.search_data.model.MoviesResponse
import com.example.search_data.network.SearchAPI
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchRepositoryImplTest {
    private lateinit var repository: SearchRepositoryImpl
    private val api: SearchAPI = mockk()

    @Before
    fun setUp() {
        repository = SearchRepositoryImpl(api)
    }

    @Test
    fun `searchMovie returns movies`() = runBlocking {
        val query = "Inception"
        val movieDto = MovieDto(
            adult = false,
            backdrop_path = "/path.jpg",
            genre_ids = listOf(1, 2),
            id = 1L,
            original_language = "en",
            original_title = "Inception",
            overview = "Overview",
            popularity = 100.0,
            poster_path = "/poster.jpg",
            release_date = "2010-07-16",
            title = "Inception",
            video = false,
            vote_average = 8.8,
            vote_count = 1000
        )
        val response = MoviesResponse(
            page = 1,
            results = listOf(movieDto),
            total_pages = 1,
            total_results = 1
        )
        coEvery { api.searchMovie(query) } returns response
        val result = repository.searchMovie(query)
        assertEquals(1, result.size)
        assertEquals(1L, result[0]?.id)
        assertEquals("Inception", result[0]?.title)
        assertEquals("2010", result[0]?.release)
    }

    @Test
    fun `searchMovie returns empty list`() = runBlocking {
        val query = "Unknown"
        val response = MoviesResponse(
            page = 1,
            results = null,
            total_pages = 1,
            total_results = 0
        )
        coEvery { api.searchMovie(query) } returns response
        val result = repository.searchMovie(query)
        assertEquals(0, result.size)
    }
}