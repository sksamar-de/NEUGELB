package com.example.details_domain.repository

import com.example.details_domain.model.Detail

interface DetailRepository {
    suspend fun getDetail(id: Int): Detail
}