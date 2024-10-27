package com.dokari4.githubapicompose.ui.components.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dokari4.githubapicompose.ui.components.lottie.ErrorLottie

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onClickRetry: () -> Unit,
    isButtonEnabled: Boolean = true,
    textButton: String,
) {
    Column(
        modifier = modifier
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
            ErrorLottie()
        }
        Text(text = errorMessage, textAlign = TextAlign.Center)
        if (isButtonEnabled) {
            Button(onClick = onClickRetry) {
                Text(text = textButton)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorContentPreview() {
    ErrorContent(errorMessage = "Something went wrong", onClickRetry = { }, textButton = "Retry")
}