package com.example.details_data.repository

import com.example.details_data.model.DetailResponse
import com.example.details_data.network.DetailApi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DetailRepositoryImplTest {
    private lateinit var repository: DetailRepositoryImpl
    private val api: DetailApi = mockk()

    @Before
    fun setUp() {
        repository = DetailRepositoryImpl(api)
    }

    @Test
    fun `getDetail returns detail from API`() = runBlocking {
        val detailResponse = DetailResponse(
            adult = false,
            backdrop_path = "/backdrop",
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
        coEvery { api.getDetail(123L) } returns detailResponse
        val result = repository.getDetail(123L)
        assertEquals(123L, result.id)
        assertEquals("Title", result.title)
    }
}