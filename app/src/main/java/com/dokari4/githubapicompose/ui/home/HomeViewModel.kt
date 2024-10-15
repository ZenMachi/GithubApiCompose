package com.dokari4.githubapicompose.ui.home

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dokari4.githubapicompose.data.Repository
import com.dokari4.githubapicompose.data.remote.network.ApiResponse
import com.dokari4.githubapicompose.data.remote.dto.UserDto
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
                        userDtos = emptyList(),
                        isLoading = true,
                    )
                }
                is ApiResponse.Error -> {
                    _users.value = HomeUiState(
                        userDtos = emptyList(),
                        isLoading = false,
                        errorMessage = response.errorMessage
                    )
                }

                is ApiResponse.Success -> {
                    _users.value = HomeUiState(
                        userDtos = response.data,
                        isLoading = false,
                    )
                }
            }
        }

    }
}

data class HomeUiState(
    val userDtos: List<UserDto> = emptyList(),
    val errorMessage: String = "",
    val isLoading: Boolean = true
)