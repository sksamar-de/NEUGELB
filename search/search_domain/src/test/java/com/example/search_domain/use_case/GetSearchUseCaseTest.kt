package com.example.search_domain.use_case

import com.example.common_utls.Resource
import com.example.search_domain.model.Movie
import com.example.search_domain.repository.SearchRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetSearchUseCaseTest {
    private lateinit var getSearchUseCase: GetSearchUseCase
    private val repository: SearchRepository = mockk()

    @Before
    fun setUp() {
        getSearchUseCase = GetSearchUseCase(repository)
    }

    @Test
    fun `invoke emits success`() = runBlocking {
        val query = "Inception"
        val movies = listOf(Movie(1L, "Inception", "2010"))
        coEvery { repository.searchMovie(query) } returns movies
        val results = getSearchUseCase.invoke(query).toList()
        assertEquals(2, results.size)
        assertTrue(results[0] is Resource.Loading)
        assertTrue(results[1] is Resource.Success)
        assertEquals(movies, (results[1] as Resource.Success).result)
    }

    @Test
    fun `invoke emits error`() = runBlocking {
        val query = "Inception"
        val errorMessage = "Network Error"
        coEvery { repository.searchMovie(query) } throws Exception(errorMessage)
        val results = getSearchUseCase.invoke(query).toList()
        assertEquals(2, results.size)
        assertTrue(results[0] is Resource.Loading)
        assertTrue(results[1] is Resource.Error)
        assertEquals(errorMessage, (results[1] as Resource.Error).message)
    }

    @Test
    fun `invoke emits error with default message`() = runBlocking {
        val query = "Inception"
        coEvery { repository.searchMovie(query) } throws Exception()
        val results = getSearchUseCase.invoke(query).toList()
        assertEquals(2, results.size)
        assertTrue(results[0] is Resource.Loading)
        assertTrue(results[1] is Resource.Error)
        assertEquals("Some Error", (results[1] as Resource.Error).message)
    }
}