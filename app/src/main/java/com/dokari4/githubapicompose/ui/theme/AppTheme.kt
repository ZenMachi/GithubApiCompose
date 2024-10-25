package com.dokari4.githubapicompose.ui.theme

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.dokari4.githubapicompose.data.local.Theme
import com.dokari4.githubapicompose.ui.settings.SettingsViewModel

@Composable
fun AppTheme(
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    content: @Composable () -> Unit
) {
    val theme by settingsViewModel.theme.collectAsState()

    val darkTheme = when (theme) {
        Theme.AUTO -> isSystemInDarkTheme()
        Theme.DARK_MODE -> true
        Theme.LIGHT_MODE -> false
    }

    GithubApiComposeTheme(
        darkTheme = darkTheme
    ) {
        content()
    }
}