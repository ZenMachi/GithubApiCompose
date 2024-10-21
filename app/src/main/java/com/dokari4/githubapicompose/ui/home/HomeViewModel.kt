package com.dokari4.githubapicompose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dokari4.githubapicompose.data.Repository
import com.dokari4.githubapicompose.data.remote.dto.UserDto
import com.dokari4.githubapicompose.data.remote.network.ApiResponse
import com.dokari4.githubapicompose.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow<UIState<List<UserDto>>>(UIState.Initial)
    val state = _state.asStateFlow()

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            val response = repository.fetchUsers()
            response.collect {
                handleState(it)
            }
        }
    }

    private fun handleState(response: ApiResponse<List<UserDto>>) {
        _state.value = when (response) {
            ApiResponse.Loading -> UIState.Loading
            is ApiResponse.Empty -> UIState.Empty
            is ApiResponse.Error -> UIState.Error(response.errorMessage)
            is ApiResponse.Success -> UIState.Success(response.data)
        }
    }
}