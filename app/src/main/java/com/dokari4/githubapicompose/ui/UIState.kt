package com.dokari4.githubapicompose.ui


sealed class UIState<out T> {
    data object Initial : UIState<Nothing>()
    data object Loading : UIState<Nothing>()
    data class Success<out T>(val data: T) : UIState<T>()
    data class Error(val errorMessage: String) : UIState<Nothing>()
    data object Empty : UIState<Nothing>()
}