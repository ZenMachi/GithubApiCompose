package com.dokari4.githubapicompose.ui.search

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
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow<UIState<List<UserDto>>>(UIState.Initial)
    val state = _state.asStateFlow()

    fun searchUser(query: String) {
        viewModelScope.launch {
            val response = repository.searchUsers(query)

            response.collect { result ->
                _state.value = when (result) {
                    ApiResponse.Loading -> UIState.Loading
                    is ApiResponse.Success -> UIState.Success(result.data.items)
                    is ApiResponse.Error -> UIState.Error(errorMessage = result.errorMessage)
                    ApiResponse.Empty -> UIState.Empty
                }
            }
        }
    }
}