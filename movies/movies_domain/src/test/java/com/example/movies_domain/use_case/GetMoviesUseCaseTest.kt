package com.example.movies_domain.use_case

import com.example.common_utls.Resource
import com.example.movies_domain.model.Movies
import com.example.movies_domain.repository.MoviesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetMoviesUseCaseTest {
    private lateinit var getMoviesUseCase: GetMoviesUseCase
    private val repository: MoviesRepository = mockk()

    @Before
    fun setUp() {
        getMoviesUseCase = GetMoviesUseCase(repository)
    }

    @Test
    fun `invoke emits success`() = runBlocking {
        val movies = Movies(1L, emptyList(), 1L, 0L)
        coEvery { repository.getMovies(1) } returns movies
        val results = getMoviesUseCase(1).toList()
        assertEquals(2, results.size)
        assertTrue(results[0] is Resource.Loading)
        assertTrue(results[1] is Resource.Success)
        assertEquals(movies, (results[1] as Resource.Success).result)
    }

    @Test
    fun `invoke emits error`() = runBlocking {
        val errorMessage = "Network Error"
        coEvery { repository.getMovies(1) } throws Exception(errorMessage)
        val results = getMoviesUseCase(1).toList()
        assertEquals(2, results.size)
        assertTrue(results[0] is Resource.Loading)
        assertTrue(results[1] is Resource.Error)
        assertEquals(errorMessage, (results[1] as Resource.Error).message)
    }
}