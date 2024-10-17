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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    var listState = LazyListState()

    init {
        getUsers()
    }

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
                _state.update {
                    it.copy(
                        isLoading = true
                    )
                }
            }
            is ApiResponse.Empty -> {
                _state.update {
                    it.copy(
                        users = emptyList(),
                        isLoading = false
                    )
                }
            }
            is ApiResponse.Error -> {
                _state.update {
                    it.copy(
                        users = emptyList(),
                        isLoading = false,
                        errorMessage = response.errorMessage
                    )
                }
            }
            is ApiResponse.Success -> {
                _state.update {
                    it.copy(
                        users = response.data,
                        isLoading = false
                    )
                }
            }
        }
    }
}

data class HomeScreenState(
    val users: List<UserDto> = emptyList(),
    val errorMessage: String = "",
    val isLoading: Boolean = true
)