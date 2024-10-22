package com.dokari4.githubapicompose.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.dokari4.githubapicompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_rounded_brightness_6_24),
                contentDescription = null
            )
            Text(text = "Settings")
        }

        var showDialog by remember { mutableStateOf(false) }

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
                        Row(
                            modifier = Modifier
                                .padding(vertical = 8.dp, horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            RadioButton(
                                selected = true,
                                onClick = { /*TODO*/ }
                            )
                            Text(text = "Light")
                        }
                        Row(
                            modifier = Modifier
                                .padding(vertical = 8.dp, horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            RadioButton(
                                selected = true,
                                onClick = { /*TODO*/ }
                            )
                            Text(text = "Light")
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .clickable { showDialog = true },
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_rounded_brightness_6_24),
                contentDescription = null
            )
            Column {
                Text(text = "Theme")
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Light",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}