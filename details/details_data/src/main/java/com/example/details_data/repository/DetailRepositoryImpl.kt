package com.example.details_data.repository

import com.example.details_data.mapper.toDetail
import com.example.details_data.network.DetailApi
import com.example.details_domain.model.Detail
import com.example.details_domain.repository.DetailRepository

class DetailRepositoryImpl(private val api: DetailApi) : DetailRepository {

    override suspend fun getDetail(id: Long): Detail {
        return api.getDetail(id = id).toDetail()
    }

}