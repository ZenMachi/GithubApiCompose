package com.dokari4.githubapicompose.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dokari4.githubapicompose.data.Repository
import com.dokari4.githubapicompose.data.remote.dto.DetailUserDto
import com.dokari4.githubapicompose.data.remote.dto.UserDto
import com.dokari4.githubapicompose.data.remote.network.ApiResponse
import com.dokari4.githubapicompose.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _detailUserState = MutableStateFlow<UIState<DetailUserDto>>(UIState.Loading)
    val detailUserState = _detailUserState.asStateFlow()

    private val _followingUserState = MutableStateFlow<UIState<List<UserDto>>>(UIState.Loading)
    val followingUserState = _followingUserState.asStateFlow()

    private val _followersUserState = MutableStateFlow<UIState<List<UserDto>>>(UIState.Loading)
    val followersUserState = _followersUserState.asStateFlow()

    private val _isFavoriteState = MutableStateFlow(false)
    val isFavoriteState = _isFavoriteState.asStateFlow()

    fun getDetailUser(username: String) {
        viewModelScope.launch {
            fetchUserData(_detailUserState) {
                repository.fetchDetailUser(username)
            }
        }
    }

    fun getFollowersUser(username: String) {
        viewModelScope.launch {
           fetchUserData(_followersUserState) {
               repository.fetchFollowers(username)
           }
        }
    }

    fun getFollowingUser(username: String) {
        viewModelScope.launch {
            fetchUserData(_followingUserState) {
                repository.fetchFollowing(username)
            }
        }
    }

    fun isFavorite(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.isFavorite(username).collect {
                withContext(Dispatchers.Main) {
                    _isFavoriteState.value = it
                }
            }
        }
    }

    fun setFavorite(data: DetailUserDto) {
        viewModelScope.launch {
            repository.addFavorite(data)
        }
    }

    fun deleteFavorite(userId: Int) {
        viewModelScope.launch {
            repository.deleteFavorite(userId)
        }
    }

    fun changeFavoriteState(state: Boolean) {
        _isFavoriteState.value = state
    }

    private fun <T> fetchUserData(
        state: MutableStateFlow<UIState<T>>,
        apiCall: suspend () -> Flow<ApiResponse<T>>
    ) {
        viewModelScope.launch {
            apiCall().collect { result ->
                state.value = when (result) {
                    ApiResponse.Loading -> UIState.Loading
                    is ApiResponse.Success -> UIState.Success(result.data)
                    is ApiResponse.Error -> UIState.Error(result.errorMessage)
                    ApiResponse.Empty -> UIState.Empty
                }
            }
        }
    }
}