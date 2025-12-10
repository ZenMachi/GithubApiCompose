package com.dokari4.githubapicompose.ui.components.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dokari4.githubapicompose.R
import com.dokari4.githubapicompose.ui.theme.GithubApiComposeTheme

@Composable
fun RowOption(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
    currentValue: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
        Column {
            Text(text = title)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = currentValue,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RowOptionPreview() {
    GithubApiComposeTheme {
        RowOption(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            icon = painterResource(id = R.drawable.ic_rounded_brightness_6_24),
            title = "Title",
            currentValue = "Current Value",
            onClick = {}
        )
    }
}