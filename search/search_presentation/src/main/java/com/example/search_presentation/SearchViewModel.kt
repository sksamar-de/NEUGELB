package com.example.search_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_utls.Resource
import com.example.search_domain.use_case.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class SearchViewModel
@Inject constructor(private val useCase: GetSearchUseCase) : ViewModel() {

    var search by mutableStateOf("")
    private var _state = MutableStateFlow<SearchState>(SearchState())
    val state
        get() = _state

    fun reset(){
        _state.value = SearchState()
    }

    fun searchMovies(){
        useCase.invoke(query = search.trim()).onEach {
            when(it){
                Resource.Loading -> {
                    _state.value = SearchState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = SearchState(searchList = it.result, error = if (it.result.isEmpty()) "$search not found." else "")
                }
                is Resource.Error<*> -> {
                    _state.value = SearchState(error = it.message)
                }
            }
        }.launchIn(viewModelScope)
    }




}