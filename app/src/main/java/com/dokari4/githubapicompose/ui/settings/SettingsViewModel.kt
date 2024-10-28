package com.dokari4.githubapicompose.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dokari4.githubapicompose.data.Repository
import com.dokari4.githubapicompose.utils.Theme
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
    private val _isDynamicColor = MutableStateFlow(true)
    val isDynamicColor = _isDynamicColor.asStateFlow()

    init{
        viewModelScope.launch {
            repository.getAppTheme().collect{
                _theme.value = it
            }
        }
        viewModelScope.launch {
            repository.getDynamicColor().collect{
                _isDynamicColor.value = it
            }
        }
    }

    fun setTheme(theme: Theme) {
        viewModelScope.launch {
            repository.saveAppTheme(theme)
        }
    }

    fun setDynamicColor(isDynamic: Boolean) {
        viewModelScope.launch {
            repository.saveDynamicColor(isDynamic)
        }
    }
}