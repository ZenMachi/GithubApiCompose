package com.dokari4.githubapicompose.ui.home

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dokari4.githubapicompose.data.Repository
import com.dokari4.githubapicompose.data.remote.network.ApiResponse
import com.dokari4.githubapicompose.data.remote.response.UsersItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = Repository()

    private val _users = MutableStateFlow(HomeUiState())
    val users = _users.asStateFlow()

    var listState = LazyListState()

    fun getUsers() {
        viewModelScope.launch {
            when (val response = repository.getUsers()) {
                is ApiResponse.Empty -> {
                    _users.value = HomeUiState(
                        users = emptyList(),
                        isLoading = true,
                        errorMessage = ""
                    )
                }
                is ApiResponse.Error -> {
                    _users.value = HomeUiState(
                        users = emptyList(),
                        isLoading = false,
                        errorMessage = response.errorMessage
                    )
                }

                is ApiResponse.Success -> {
                    _users.value = HomeUiState(
                        users = response.data,
                        isLoading = false,
                        errorMessage = ""
                    )
                }
            }
        }

    }
}

data class HomeUiState(
    val users: List<UsersItem> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String = ""
)