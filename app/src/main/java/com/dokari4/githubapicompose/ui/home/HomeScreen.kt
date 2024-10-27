package com.dokari4.githubapicompose.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dokari4.githubapicompose.ui.UIState
import com.dokari4.githubapicompose.ui.components.CardItem
import com.dokari4.githubapicompose.ui.components.ShowProgressBar
import com.dokari4.githubapicompose.ui.components.error.ErrorContent
import com.dokari4.githubapicompose.utils.SnackbarController
import com.dokari4.githubapicompose.utils.SnackbarEvent
import com.dokari4.githubapicompose.ui.components.lottie.ErrorLottie

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onCardClick: (String) -> Unit
) {
    val uiState by viewModel.state.collectAsState()
    val state = uiState

    /**
     * Launched Effect Cause function to be called everytime the composable is called
     * Better call the function without using Launched Effect
     */
//    LaunchedEffect(Unit) {
//        viewModel.getUsers()
//    }

    /**
     * Calling viewModel in the composable function only make infinite loop
     * Better Call in the init block of the viewModel
     */
//    viewModel.getUsers()

    Column {

        when (state) {
            UIState.Initial -> {}
            UIState.Loading -> ShowProgressBar()
            is UIState.Success -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(state.data) { index, data ->
                        val lastPadding = if (index == state.data.lastIndex) 16.dp else 0.dp
                        CardItem(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(bottom = lastPadding)
                                .fillMaxWidth()
                                .clickable(onClick = { onCardClick(data.idName) }),
                            data = data
                        )
                    }
                }
            }

            is UIState.Error -> {
                LaunchedEffect(Unit) {
                    SnackbarController.sendEvent(
                        event = SnackbarEvent(
                            message = state.errorMessage,
                            action = null
                        )
                    )
                }
                ErrorContent(
                    errorMessage = state.errorMessage,
                    onClickRetry = { viewModel.getUsers() },
                    textButton = "Retry"
                )
            }

            UIState.Empty -> {}
        }
    }
}