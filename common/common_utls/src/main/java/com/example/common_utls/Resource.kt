package com.example.common_utls

sealed class Resource<out T> {
    data object Loading : Resource<Nothing>()
    data class Success<T>(val result: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
}
