package com.example.movies_domain.use_case

import com.example.movies_domain.model.Movie
import com.example.movies_domain.model.Movies
import com.example.movies_domain.repository.MoviesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class GetMoviesUseCaseTest {
    private lateinit var getMoviesUseCase: GetMoviesUseCase
    private val repository: MoviesRepository = mockk()

    @Before
    fun setUp() {
        getMoviesUseCase = GetMoviesUseCase(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke returns flow that emits PagingData when repository returns movies`() = runTest {
        val movie = Movie(id = 1L, original_language = "en", poster_path = "/poster", backdrop_path = "/backdrop", release_date = "2021", title = "Title", overview = "Overview", vote_average = 8.0, popularity = 1.0)
        coEvery { repository.getMovies(1) } returns Movies(1L, listOf(movie), 1L, 1L)

        val pagingData = getMoviesUseCase.invoke().first()

        assertNotNull(pagingData)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke returns flow that emits PagingData for multiple pages`() = runTest {
        val page1Movie = Movie(id = 1L, original_language = "en", poster_path = "/p1", backdrop_path = "/b1", release_date = "2021", title = "Movie 1", overview = "O1", vote_average = 8.0, popularity = 1.0)
        val page2Movie = Movie(id = 2L, original_language = "en", poster_path = "/p2", backdrop_path = "/b2", release_date = "2021", title = "Movie 2", overview = "O2", vote_average = 7.0, popularity = 2.0)
        coEvery { repository.getMovies(1) } returns Movies(1L, listOf(page1Movie), 2L, 2L)
        coEvery { repository.getMovies(2) } returns Movies(2L, listOf(page2Movie), 2L, 2L)

        val pagingData = getMoviesUseCase.invoke().first()

        assertNotNull(pagingData)
    }
}