package com.dokari4.githubapicompose.ui.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dokari4.githubapicompose.data.Repository
import com.dokari4.githubapicompose.data.remote.dto.UserDto
import com.dokari4.githubapicompose.data.remote.network.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(SearchScreenState())
    val state = _state.asStateFlow()

    fun searchUser(query: String) {
        viewModelScope.launch {
            val response = repository.searchUsers(query)
            response.collect { result ->
                when (result) {
                    ApiResponse.Loading -> {
                        _state.update {
                            it.copy(
                                users = emptyList(),
                                firstTime = false,
                                isLoading = true
                            )
                        }
                    }

                    is ApiResponse.Success -> {
                        _state.update {
                            it.copy(
                                users = result.data.items,
                                firstTime = false,
                                isLoading = false
                            )
                        }
                    }

                    is ApiResponse.Error -> {
                        _state.update {
                            it.copy(
                                users = emptyList(),
                                errorMessage = result.errorMessage,
                                firstTime = false,
                                isLoading = false
                            )
                        }
                    }
                    ApiResponse.Empty -> {
                        _state.update {
                            it.copy(
                                users = emptyList(),
                                firstTime = false,
                                isLoading = false,
                            )
                        }
                    }
                }
            }
        }
    }
}

data class SearchScreenState(
    val users: List<UserDto> = emptyList(),
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val firstTime: Boolean = true,
)