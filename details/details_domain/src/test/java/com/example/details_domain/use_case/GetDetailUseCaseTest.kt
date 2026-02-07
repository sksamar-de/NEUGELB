package com.example.details_domain.use_case

import com.example.common_utls.Resource
import com.example.details_domain.model.Detail
import com.example.details_domain.repository.DetailRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetDetailUseCaseTest {
    private lateinit var getDetailUseCase: GetDetailUseCase
    private val repository: DetailRepository = mockk()

    @Before
    fun setUp() {
        getDetailUseCase = GetDetailUseCase(repository)
    }

    @Test
    fun `invoke emits success`() = runBlocking {
        val detail = Detail(
            adult = false,
            backdrop_path = "/path",
            belongs_to_collection = null,
            budget = 1000,
            genres = emptyList(),
            homepage = "",
            id = 123L,
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
        coEvery { repository.getDetail(123L) } returns detail
        val results = getDetailUseCase.invoke(123L).toList()
        assertEquals(2, results.size)
        assertTrue(results[0] is Resource.Loading)
        assertTrue(results[1] is Resource.Success)
        assertEquals(detail, (results[1] as Resource.Success).result)
    }

    @Test
    fun `invoke emits error`() = runBlocking {
        val errorMessage = "Network Error"
        coEvery { repository.getDetail(123L) } throws Exception(errorMessage)
        val results = getDetailUseCase.invoke(123L).toList()
        assertEquals(2, results.size)
        assertTrue(results[0] is Resource.Loading)
        assertTrue(results[1] is Resource.Error)
        assertEquals(errorMessage, (results[1] as Resource.Error).message)
    }
}