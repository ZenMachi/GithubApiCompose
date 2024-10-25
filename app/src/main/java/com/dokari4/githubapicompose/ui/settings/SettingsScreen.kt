package com.dokari4.githubapicompose.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dokari4.githubapicompose.R
import com.dokari4.githubapicompose.ui.components.settings.RowOption
import com.dokari4.githubapicompose.ui.components.settings.ThemeSelectionDialog

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    var showThemeDialog by remember { mutableStateOf(false) }
    val theme by viewModel.theme.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        RowOption(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            icon = painterResource(R.drawable.ic_rounded_brightness_6_24),
            title = "Theme",
            currentValue = theme.displayName,
            onClick = { showThemeDialog = true }
        )
        if (showThemeDialog) {
            ThemeSelectionDialog(
                currentTheme = theme,
                onThemeSelected = {
                    viewModel.setTheme(it)
                    showThemeDialog = false
                },
                onDismissRequest = { showThemeDialog = false }
            )
        }
        //TODO: Add Row Slider Option for Dynamic Color
    }
}