package com.example.details_data.mapper

import com.example.details_data.model.BelongsToCollection
import com.example.details_data.model.DetailResponse
import com.example.details_data.model.Genre
import com.example.details_data.model.ProductionCompany
import com.example.details_data.model.ProductionCountry
import com.example.details_data.model.SpokenLanguage
import com.example.details_domain.model.Detail
import com.example.details_domain.model.Genre as DomainGenre
import com.example.details_domain.model.BelongsToCollection as DomainBelongsToCollection
import com.example.details_domain.model.ProductionCompany as DomainProductionCompany
import com.example.details_domain.model.ProductionCountry as DomainProductionCountry
import com.example.details_domain.model.SpokenLanguage as DomainSpokenLanguage

fun DetailResponse.toDetail(): Detail {
    return Detail(
        adult = adult,
        backdrop_path = backdrop_path,
        belongs_to_collection = belongs_to_collection?.toDomain(),
        budget = budget,
        genres = genres?.map { it?.toDomain() },
        homepage = homepage,
        id = id,
        imdb_id = imdb_id,
        origin_country = origin_country,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        production_companies = production_companies?.map { it?.toDomain() },
        production_countries = production_countries?.map { it?.toDomain() },
        release_date = release_date,
        revenue = revenue,
        runtime = runtime,
        spoken_languages = spoken_languages?.map { it?.toDomain() },
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        vote_average = vote_average,
        vote_count = vote_count
    )
}

fun Genre.toDomain(): DomainGenre {
    return DomainGenre(
        id = id,
        name = name
    )
}

fun BelongsToCollection.toDomain(): DomainBelongsToCollection {
    return DomainBelongsToCollection(
        backdrop_path = backdrop_path,
        id = id,
        name = name,
        poster_path = poster_path
    )
}

fun ProductionCompany.toDomain(): DomainProductionCompany {
    return DomainProductionCompany(
        id = id,
        logo_path = logo_path,
        name = name,
        origin_country = origin_country
    )
}

fun ProductionCountry.toDomain(): DomainProductionCountry {
    return DomainProductionCountry(
        iso_3166_1 = iso_3166_1,
        name = name
    )
}

fun SpokenLanguage.toDomain(): DomainSpokenLanguage {
    return DomainSpokenLanguage(
        english_name = english_name,
        iso_639_1 = iso_639_1,
        name = name
    )
}
