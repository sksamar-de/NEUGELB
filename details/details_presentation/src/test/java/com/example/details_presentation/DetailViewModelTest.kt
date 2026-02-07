package com.example.details_presentation

import androidx.lifecycle.SavedStateHandle
import com.example.common_utls.Resource
import com.example.details_domain.model.Detail
import com.example.details_domain.use_case.GetDetailUseCase
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
class DetailViewModelTest {
    private val useCase: GetDetailUseCase = mockk()
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
        val id = 123L
        val handle = SavedStateHandle(mapOf("id" to id))
        every { useCase.invoke(id) } returns flow { emit(Resource.Loading) }
        val viewModel = DetailViewModel(handle, useCase)
        assertTrue(viewModel.state.isLoading)
        assertEquals("", viewModel.state.error)
    }

    @Test
    fun `getDetails updates state to Success`() = runTest {
        val id = 123L
        val handle = SavedStateHandle(mapOf("id" to id))
        val detail = Detail(
            adult = false,
            backdrop_path = "/path",
            belongs_to_collection = null,
            budget = 1000,
            genres = emptyList(),
            homepage = "",
            id = id,
            imdb_id = "tt123",
            origin_country = emptyList(),
            original_language = "en",
            original_title = "Title",
            overview = "Overview",
            popularity = 10.0,
            poster_path = "/poster",
            production_companies = emptyList(),
            production_countries = emptyList(),
            release_date = "2023-01-01",
            revenue = 2000,
            runtime = 120,
            spoken_languages = emptyList(),
            status = "Released",
            tagline = "Tagline",
            title = "Title",
            video = false,
            vote_average = 8.5,
            vote_count = 100
        )
        every { useCase.invoke(id) } returns flow {
            emit(Resource.Loading)
            emit(Resource.Success(detail))
        }
        val viewModel = DetailViewModel(handle, useCase)
        assertFalse(viewModel.state.isLoading)
        assertEquals(detail, viewModel.state.detail)
        assertEquals("", viewModel.state.error)
    }

    @Test
    fun `getDetails updates state to Error`() = runTest {
        val id = 123L
        val handle = SavedStateHandle(mapOf("id" to id))
        val errorMessage = "Error message"
        every { useCase.invoke(id) } returns flow {
            emit(Resource.Loading)
            emit(Resource.Error<Detail>(errorMessage))
        }
        val viewModel = DetailViewModel(handle, useCase)
        assertFalse(viewModel.state.isLoading)
        assertEquals(errorMessage, viewModel.state.error)
    }
}