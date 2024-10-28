package com.dokari4.githubapicompose.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dokari4.githubapicompose.data.Repository
import com.dokari4.githubapicompose.data.local.model.FavoriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow<List<FavoriteEntity>>(emptyList())
    val state = _state.asStateFlow()

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavoriteUsers().collect() {
                withContext(Dispatchers.Main) {
                    _state.value = it
                }
            }
        }
    }

    fun deleteFavorite(userId: Int) {
        viewModelScope.launch {
            repository.deleteFavorite(userId)
        }
    }
}