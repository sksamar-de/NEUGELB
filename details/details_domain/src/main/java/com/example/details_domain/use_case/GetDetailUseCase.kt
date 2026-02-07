package com.example.details_domain.use_case

import com.example.common_utls.Resource
import com.example.details_domain.model.Detail
import com.example.details_domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDetailUseCase(private val repository: DetailRepository) {

    fun invoke(id: Long): Flow<Resource<Detail>> = flow {
        emit(Resource.Loading)
        try {
            val response = repository.getDetail(id = id)
            emit(Resource.Success( result = response))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: "Some Error"))
        }
    }
}