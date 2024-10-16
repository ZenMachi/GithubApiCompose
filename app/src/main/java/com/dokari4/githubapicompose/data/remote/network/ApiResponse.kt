package com.dokari4.githubapicompose.data.remote.network

sealed class ApiResponse<out T> {
    data object Loading : ApiResponse<Nothing>()
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
    data object Empty : ApiResponse<Nothing>()
}