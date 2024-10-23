package com.dokari4.githubapicompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dokari4.githubapicompose.R
import com.dokari4.githubapicompose.data.local.Theme
import com.dokari4.githubapicompose.ui.settings.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowOption(
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    //TODO: Make this composable function more reusable
    var showDialog by remember { mutableStateOf(false) }
    val theme by settingsViewModel.theme.collectAsStateWithLifecycle()

    if (showDialog) {
        BasicAlertDialog(
            onDismissRequest = { showDialog = false },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column {
                    var selectedOption by remember { mutableStateOf(theme) }
                    Theme.entries.forEach {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedOption = it },
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            RadioButton(
                                selected = it == selectedOption,
                                onClick = { selectedOption = it }
                            )
                            Text(text = it.displayName)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = { showDialog = false },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(text = "Cancel")
                        }
                        TextButton(
                            onClick = {
                                showDialog = false
                                settingsViewModel.setTheme(selectedOption)
                            },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(text = "OK")
                        }
                    }
                }
            }
        }
    }

    Row(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 16.dp)
            .fillMaxWidth()
            .clickable { showDialog = true },
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_rounded_brightness_6_24),
            contentDescription = null
        )
        Column {
            Text(text = "Theme")
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = theme.displayName,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}