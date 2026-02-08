package com.example.movies_presentation

import androidx.paging.PagingData
import com.example.movies_domain.model.Movie
import com.example.movies_domain.use_case.GetMoviesUseCase
import app.cash.turbine.test
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertNotNull
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
    fun `movies flow emits PagingData when use case returns data`() = runTest {
        val testMovie = Movie(id = 1L, original_language = "en", poster_path = "/poster", backdrop_path = "/backdrop", release_date = "2021", title = "Title", overview = "Overview", vote_average = 8.0, popularity = 1.0)
        every { useCase.invoke() } returns flowOf(PagingData.from(listOf(testMovie)))
        val viewModel = MovieViewModel(useCase)
        viewModel.movies.test {
            val pagingData = awaitItem()
            assertNotNull(pagingData)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `useCase invoke is called when movies flow is collected`() = runTest {
        every { useCase.invoke() } returns flowOf(PagingData.from(emptyList<Movie>()))
        val viewModel = MovieViewModel(useCase)
        viewModel.movies.test {
            awaitItem()
            cancelAndIgnoreRemainingEvents()
        }
        verify(exactly = 1) { useCase.invoke() }
    }

    @Test
    fun `movies flow emits from use case and is cached`() = runTest {
        val movies = listOf(
            Movie(id = 1L, original_language = "en", poster_path = "/poster", backdrop_path = "/backdrop", release_date = "2021", title = "Title 1", overview = "Overview 1", vote_average = 8.0, popularity = 1.0),
            Movie(id = 2L, original_language = "en", poster_path = "/poster2", backdrop_path = "/backdrop2", release_date = "2021", title = "Title 2", overview = "Overview 2", vote_average = 7.0, popularity = 2.0)
        )
        every { useCase.invoke() } returns flowOf(PagingData.from(movies))
        val viewModel = MovieViewModel(useCase)
        viewModel.movies.test {
            val first = awaitItem()
            assertNotNull(first)
            cancelAndIgnoreRemainingEvents()
        }
        viewModel.movies.test {
            val cached = awaitItem()
            assertNotNull(cached)
            cancelAndIgnoreRemainingEvents()
        }
        verify(exactly = 1) { useCase.invoke() }
    }
}