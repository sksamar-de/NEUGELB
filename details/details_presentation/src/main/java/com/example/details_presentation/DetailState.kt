package com.example.details_presentation

import com.example.details_domain.model.Detail

data class DetailState(
    val isLoading: Boolean = false,
    val detail: Detail? = null,
    val error: String = ""
)
