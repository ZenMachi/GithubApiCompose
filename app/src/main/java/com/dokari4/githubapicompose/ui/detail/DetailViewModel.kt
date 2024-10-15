package com.dokari4.githubapicompose.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dokari4.githubapicompose.data.Repository
import com.dokari4.githubapicompose.data.remote.dto.DetailUserDto
import com.dokari4.githubapicompose.data.remote.network.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {
    private val repository = Repository()

    private val _state = MutableStateFlow(DetailUiState())
    val state = _state.asStateFlow()

    fun getDetailUser(username: String) {
        viewModelScope.launch {
            when (val response = repository.fetchDetailUser(username)) {
                is ApiResponse.Empty -> {
                    _state.value = DetailUiState(
                        isLoading = true,
                    )
                }
                is ApiResponse.Error -> {
                    _state.value = DetailUiState(
                        isLoading = false,
                        errorMessage = response.errorMessage
                    )
                }
                is ApiResponse.Success -> {
                    _state.value = DetailUiState(
                        data = response.data,
                        isLoading = false,
                    )
                }
            }
        }
    }
}

data class DetailUiState(
    val data: DetailUserDto? = null,
    val errorMessage: String = "",
    val isLoading: Boolean = true
)