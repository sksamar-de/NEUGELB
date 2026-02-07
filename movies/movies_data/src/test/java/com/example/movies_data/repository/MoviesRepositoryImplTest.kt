package com.example.movies_data.repository

import com.example.movies_data.model.MoviesResponse
import com.example.movies_data.network.MoviesApi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MoviesRepositoryImplTest {
    private lateinit var repository: MoviesRepositoryImpl
    private val api: MoviesApi = mockk()

    @Before
    fun setUp() {
        repository = MoviesRepositoryImpl(api)
    }

    @Test
    fun `getMovies returns movies from API`() = runBlocking {
        val response = MoviesResponse(
            page = 1,
            results = emptyList(),
            total_pages = 1,
            total_results = 0
        )
        coEvery { api.getMovies(1) } returns response
        val result = repository.getMovies(1)
        assertEquals(1L, result.page)
        assertEquals(0, result.results.size)
    }
}