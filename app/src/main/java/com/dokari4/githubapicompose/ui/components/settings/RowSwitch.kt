package com.dokari4.githubapicompose.ui.components.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RowSwitch(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    title: String,
    subtitle: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .alpha(if (enabled) 1f else 0.5f)
            .clickable {
                if (enabled) onClick()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = title)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = subtitle,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Switch(
            checked = isChecked,
            enabled = enabled,
            onCheckedChange = {
                onClick()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RowSwitchPreview() {
    RowSwitch(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 56.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
        isChecked = true,
        title = "Title",
        subtitle = "Subtitle",
        onClick = {}
    )
}

