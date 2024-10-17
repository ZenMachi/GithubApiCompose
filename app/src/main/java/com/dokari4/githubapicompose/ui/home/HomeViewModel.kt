package com.dokari4.githubapicompose.ui.home

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dokari4.githubapicompose.data.Repository
import com.dokari4.githubapicompose.data.remote.network.ApiResponse
import com.dokari4.githubapicompose.data.remote.dto.UserDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _users = MutableStateFlow(HomeScreenState())
    val users = _users.asStateFlow()

    var listState = LazyListState()

    fun getUsers() {
        viewModelScope.launch {
            val response = repository.fetchUsers()
            response.collect {
                handleState(it)
            }
        }
    }

    private fun handleState(response: ApiResponse<List<UserDto>>) {
        when (response) {
            ApiResponse.Loading -> {
                _users.value = HomeScreenState(
                    user = emptyList(),
                    isLoading = true,
                )
            }
            is ApiResponse.Empty -> {
                _users.value = HomeScreenState(
                    user = emptyList(),
                    isLoading = false,
                )
            }
            is ApiResponse.Error -> {
                _users.value = HomeScreenState(
                    user = emptyList(),
                    isLoading = false,
                    errorMessage = response.errorMessage
                )
            }
            is ApiResponse.Success -> {
                _users.value = HomeScreenState(
                    user = response.data,
                    isLoading = false,
                )
            }
        }
    }
}

data class HomeScreenState(
    val user: List<UserDto> = emptyList(),
    val errorMessage: String = "",
    val isLoading: Boolean = true
)