package com.dokari4.githubapicompose.ui.components.empty

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dokari4.githubapicompose.ui.components.lottie.NotFoundLottie

@Composable
fun EmptyContent(
    emptyMessage: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            NotFoundLottie()
        }
        Text(text = emptyMessage, textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyContentPreview() {
    EmptyContent("No Data Found")
}