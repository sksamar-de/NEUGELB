package com.example.movies_presentation

data class MoviesState(
    val isLoading: Boolean = false,
    val error: String = "",
    val currentPage: Int = 0,
    val totalPage: Int = 0,
    val isLoadingMore: Boolean = false
)
