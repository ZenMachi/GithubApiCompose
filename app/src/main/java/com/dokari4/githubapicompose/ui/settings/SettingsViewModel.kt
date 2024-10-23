package com.dokari4.githubapicompose.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dokari4.githubapicompose.data.Repository
import com.dokari4.githubapicompose.data.local.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _theme = MutableStateFlow(Theme.AUTO)
    val theme = _theme.asStateFlow()

    init{
        viewModelScope.launch {
            repository.getAppTheme().collect{
                _theme.value = it
            }
        }
    }

    fun setTheme(theme: Theme) {
        viewModelScope.launch {
            repository.saveAppTheme(theme)
        }
    }
}