package com.example.details_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_utls.Resource
import com.example.details_domain.model.Detail
import com.example.details_domain.use_case.GetDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    handle: SavedStateHandle,
    private val useCase: GetDetailUseCase
) : ViewModel() {

    var state by mutableStateOf(DetailState())

    init {
        val id: Long? = handle["id"]
        id?.let { getDetails(id) }
    }

    private fun getDetails(id: Long) {
        useCase.invoke(id = id).onEach {
            when(it){
                Resource.Loading -> {
                    state = DetailState(isLoading = true)
                }
                is Resource.Success<Detail> -> {
                    state = DetailState(detail = it.result)
                }
                is Resource.Error<*> -> {
                    state = DetailState(error = it.message)
                }
            }
        }.launchIn(viewModelScope)
    }

}