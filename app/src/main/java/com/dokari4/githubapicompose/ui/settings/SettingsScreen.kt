package com.dokari4.githubapicompose.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dokari4.githubapicompose.R
import com.dokari4.githubapicompose.ui.components.settings.RowOption
import com.dokari4.githubapicompose.ui.components.settings.RowSwitch
import com.dokari4.githubapicompose.ui.components.settings.ThemeSelectionDialog
import com.dokari4.githubapicompose.utils.isDynamicColorSupported

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    var showThemeDialog by remember { mutableStateOf(false) }
    val theme by viewModel.theme.collectAsStateWithLifecycle()
    val isDynamicColorEnabled by viewModel.isDynamicColor.collectAsStateWithLifecycle()
    val crashSettingIcon = rememberVectorPainter(image = Icons.Default.Warning)

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
        RowSwitch(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 56.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
            isChecked = isDynamicColorEnabled,
            title = "Dynamic Color",
            subtitle = if (isDynamicColorSupported) "Enable Dynamic Color" else "Android 12+ only",
            enabled = isDynamicColorSupported,
            onClick = {
                viewModel.setDynamicColor(!isDynamicColorEnabled)
            }
        )
        RowOption(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            icon = crashSettingIcon,
            title = "Test Crash",
            currentValue = "Crash this App",
            onClick = { viewModel.crashThisApp() }
        )
    }
}