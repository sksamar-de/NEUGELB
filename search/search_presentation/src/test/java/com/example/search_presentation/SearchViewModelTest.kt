package com.example.search_presentation

import app.cash.turbine.test
import com.example.common_utls.Resource
import com.example.search_domain.model.Movie
import com.example.search_domain.use_case.GetSearchUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
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
class SearchViewModelTest {
    private lateinit var viewModel: SearchViewModel
    private val useCase: GetSearchUseCase = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SearchViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `searchMovies updates state to Success`() = runTest {
        val query = "Inception"
        val movies = listOf(Movie(1L, "Inception", "2010"))
        viewModel.search = query
        every { useCase.invoke(query) } returns flow {
            emit(Resource.Loading)
            delay(1)
            emit(Resource.Success(movies))
        }
        viewModel.state.test {
            assertEquals(SearchState(), awaitItem())
            viewModel.searchMovies()
            assertTrue(awaitItem().isLoading)
            val successState = awaitItem()
            assertFalse(successState.isLoading)
            assertEquals(movies, successState.searchList)
            assertEquals("", successState.error)
        }
    }

    @Test
    fun `searchMovies updates state to Error`() = runTest {
        val query = "Inception"
        val errorMessage = "Error message"
        viewModel.search = query
        every { useCase.invoke(query) } returns flow {
            emit(Resource.Loading)
            delay(1)
            emit(Resource.Error<List<Movie?>>(errorMessage))
        }
        viewModel.state.test {
            assertEquals(SearchState(), awaitItem())
            viewModel.searchMovies()
            assertTrue(awaitItem().isLoading)
            val errorState = awaitItem()
            assertFalse(errorState.isLoading)
            assertEquals(errorMessage, errorState.error)
        }
    }

    @Test
    fun `searchMovies shows not found error when empty`() = runTest {
        val query = "Unknown"
        viewModel.search = query
        every { useCase.invoke(query.trim()) } returns flow {
            emit(Resource.Loading)
            delay(1)
            emit(Resource.Success(emptyList()))
        }
        viewModel.state.test {
            assertEquals(SearchState(), awaitItem())
            viewModel.searchMovies()
            assertTrue(awaitItem().isLoading)
            val successState = awaitItem()
            assertEquals("Unknown not found.", successState.error)
            assertTrue(successState.searchList.isEmpty())
        }
    }

    @Test
    fun `reset clears the state`() = runTest {
        every { useCase.invoke(any()) } returns flow { emit(Resource.Loading) }
        viewModel.searchMovies()
        viewModel.state.test {
            assertTrue(awaitItem().isLoading)
            viewModel.reset()
            assertEquals(SearchState(), awaitItem())
        }
    }
}