package com.example.search_presentation

import com.example.search_domain.model.Movie

data class SearchState(
    val isLoading: Boolean = false,
    val searchList: List<Movie?> = emptyList(),
    val error: String = ""
)
