package com.example.details_data.mapper

import com.example.details_data.model.BelongsToCollection
import com.example.details_data.model.DetailResponse
import com.example.details_data.model.Genre
import com.example.details_data.model.ProductionCompany
import com.example.details_data.model.ProductionCountry
import com.example.details_data.model.SpokenLanguage
import org.junit.Assert.assertEquals
import org.junit.Test

class MappersTest {
    @Test
    fun `toDetail maps DetailResponse to Detail`() {
        val response = DetailResponse(
            adult = false,
            backdrop_path = "/backdrop",
            belongs_to_collection = BelongsToCollection("/path", 1L, "Collection", "/poster"),
            budget = 1000,
            genres = listOf(Genre(1, "Action")),
            homepage = "home",
            id = 123L,
            imdb_id = "tt123",
            origin_country = listOf("US"),
            original_language = "en",
            original_title = "Original Title",
            overview = "Overview",
            popularity = 10.0,
            poster_path = "/poster",
            production_companies = listOf(ProductionCompany(1, "/logo", "Company", "US")),
            production_countries = listOf(ProductionCountry("US", "USA")),
            release_date = "2023-01-01",
            revenue = 2000,
            runtime = 120,
            spoken_languages = listOf(SpokenLanguage("English", "en", "English")),
            status = "Released",
            tagline = "Tagline",
            title = "Title",
            video = false,
            vote_average = 8.5,
            vote_count = 100
        )
        val detail = response.toDetail()
        assertEquals(123L, detail.id)
        assertEquals("Title", detail.title)
        assertEquals("Action", detail.genres?.first()?.name)
        assertEquals("Collection", detail.belongs_to_collection?.name)
        assertEquals("Company", detail.production_companies?.first()?.name)
        assertEquals("USA", detail.production_countries?.first()?.name)
        assertEquals("English", detail.spoken_languages?.first()?.name)
    }

    @Test
    fun `BelongsToCollection toDomain maps correctly`() {
        val collection = BelongsToCollection("/path", 1L, "Collection", "/poster")
        val domain = collection.toDomain()
        assertEquals(1L, domain.id)
        assertEquals("Collection", domain.name)
    }
}