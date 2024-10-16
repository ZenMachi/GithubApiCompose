package com.dokari4.githubapicompose.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dokari4.githubapicompose.data.Repository
import com.dokari4.githubapicompose.data.remote.dto.DetailUserDto
import com.dokari4.githubapicompose.data.remote.dto.UserDto
import com.dokari4.githubapicompose.data.remote.network.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val repository = Repository()

    private val _state = MutableStateFlow(DetailScreenState())
    val state = _state.asStateFlow()

    fun getDetailUser(username: String) {
        viewModelScope.launch {
            val response = repository.fetchDetailUser(username)
            response.collect { result ->
                when (result) {
                    ApiResponse.Loading -> {
                        _state.update {
                            it.copy(
                                isLoadingScreen = true,
                                data = null,
                            )
                        }
                    }

                    ApiResponse.Empty -> {
                        _state.update {
                            it.copy(
                                isLoadingScreen = false,
                                data = null
                            )
                        }
                    }

                    is ApiResponse.Error -> {
                        _state.update {
                            it.copy(
                                isLoadingScreen = false,
                                data = null,
                                errorMessage = result.errorMessage
                            )
                        }
                    }

                    is ApiResponse.Success -> {
                        _state.update {
                            it.copy(
                                isLoadingScreen = false,
                                data = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    fun getFollowersUser(username: String) {
        viewModelScope.launch {
            val response = repository.fetchFollowers(username)
            response.collect { result ->
                when (result) {
                    ApiResponse.Loading -> {
                        _state.update {
                            it.copy(
                                isLoadingFollowers = true,
                                listFollowers = emptyList()
                            )
                        }
                    }

                    ApiResponse.Empty -> {
                        _state.update {
                            it.copy(
                                isLoadingFollowers = false,
                                listFollowers = emptyList()
                            )
                        }
                    }

                    is ApiResponse.Error -> {
                        _state.update {
                            it.copy(
                                isLoadingFollowers = false,
                                listFollowers = emptyList(),
                                errorMessage = result.errorMessage
                            )
                        }
                    }

                    is ApiResponse.Success -> {
                        _state.update {
                            it.copy(
                                isLoadingFollowers = false,
                                listFollowers = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    fun getFollowingUser(username: String) {
        viewModelScope.launch {
            val response = repository.fetchFollowing(username)
            response.collect { result ->
                when (result) {
                    ApiResponse.Loading -> {
                        _state.update {
                            it.copy(
                                isLoadingFollowing = true,
                                listFollowing = emptyList()
                            )
                        }
                    }

                    ApiResponse.Empty -> {
                        _state.update {
                            it.copy(
                                isLoadingFollowing = false,
                                listFollowing = emptyList()
                            )
                        }
                    }

                    is ApiResponse.Error -> {
                        _state.update {
                            it.copy(
                                isLoadingFollowing = false,
                                listFollowing = emptyList(),
                                errorMessage = result.errorMessage
                            )
                        }
                    }

                    is ApiResponse.Success -> {
                        _state.update {
                            it.copy(
                                isLoadingFollowing = false,
                                listFollowing = result.data
                            )
                        }
                    }
                }
            }
        }
    }
}

data class DetailScreenState(
    val data: DetailUserDto? = null,
    val listFollowers: List<UserDto> = emptyList(),
    val listFollowing: List<UserDto> = emptyList(),
    val errorMessage: String = "",
    val isLoadingScreen: Boolean = true,
    val isLoadingFollowers: Boolean = true,
    val isLoadingFollowing: Boolean = true,
)