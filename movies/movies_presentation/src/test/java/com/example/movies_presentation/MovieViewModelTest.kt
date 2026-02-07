package com.example.movies_presentation

import com.example.common_utls.Resource
import com.example.movies_domain.model.Movie
import com.example.movies_domain.model.Movies
import com.example.movies_domain.use_case.GetMoviesUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieViewModelTest {
    private val useCase: GetMoviesUseCase = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initialization updates state to Loading`() = runTest {
        every { useCase.invoke(1) } returns flow { emit(Resource.Loading) }
        val viewModel = MovieViewModel(useCase)
        assertTrue(viewModel.currentState.isLoading)
    }

    @Test
    fun `getAllMovies updates state to Success`() = runTest {
        val movies = Movies(1L, listOf(Movie(1L, "en", "/poster", "/backdrop", "2021", "Title", "Overview", 8.0, 1.0)), 1L, 1L)
        every { useCase.invoke(1) } returns flow {
            emit(Resource.Loading)
            emit(Resource.Success(movies))
        }
        val viewModel = MovieViewModel(useCase)
        assertFalse(viewModel.currentState.isLoading)
        assertEquals(1, viewModel.movies.size)
        assertEquals(1, viewModel.currentState.currentPage)
    }

    @Test
    fun `getNextMovies updates state`() = runTest {
        val initialMovies = Movies(1L, listOf(Movie(1L, "en", "poster", "backdrop", "2021", "Title", "Overview", 8.0, 1.0)), 10L, 2L)
        every { useCase.invoke(1) } returns flow { emit(Resource.Success(initialMovies)) }
        val viewModel = MovieViewModel(useCase)
        assertEquals(1, viewModel.movies.size)
        val nextMovies = Movies(2L, listOf(Movie(2L, "en", "/poster2", "/backdrop2", "2021", "Title 2", "Overview", 8.0, 1.0)), 10L, 2L)
        every { useCase.invoke(2) } returns flow {
            emit(Resource.Loading)
            emit(Resource.Success(nextMovies))
        }
        viewModel.getNextMovies()
        assertFalse(viewModel.currentState.isLoadingMore)
        assertEquals(2, viewModel.movies.size)
        assertEquals(2, viewModel.currentState.currentPage)
    }

    @Test
    fun `getAllMovies updates state to Error`() = runTest {
        val errorMessage = "Error message"
        every { useCase.invoke(1) } returns flow {
            emit(Resource.Loading)
            emit(Resource.Error<Movies>(errorMessage))
        }
        val viewModel = MovieViewModel(useCase)
        assertFalse(viewModel.currentState.isLoading)
        assertEquals(errorMessage, viewModel.currentState.error)
    }
}