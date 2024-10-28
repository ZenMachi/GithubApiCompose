package com.dokari4.githubapicompose.ui.components.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.dokari4.githubapicompose.utils.Theme
import com.dokari4.githubapicompose.ui.theme.GithubApiComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSelectionDialog(
    currentTheme: Theme,
    onThemeSelected: (Theme) -> Unit,
    onDismissRequest: () -> Unit
) {
    var selectedOption by remember { mutableStateOf(currentTheme) }

    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
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
                Theme.entries.forEach { theme ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedOption = theme },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = theme == selectedOption,
                            onClick = { selectedOption = theme }
                        )
                        Text(text = theme.displayName)
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = onDismissRequest,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Cancel")
                    }
                    TextButton(
                        onClick = {
                            onDismissRequest()
                            onThemeSelected(selectedOption)
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

@Preview(showBackground = true)
@Composable
private fun PreviewThemeSelectionDialog() {
    GithubApiComposeTheme {
        ThemeSelectionDialog(
            currentTheme = Theme.AUTO,
            onThemeSelected = {},
            onDismissRequest = {}
        )
    }
}