package com.dokari4.githubapicompose.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dokari4.githubapicompose.ui.UIState
import com.dokari4.githubapicompose.ui.components.common.CardItem
import com.dokari4.githubapicompose.ui.components.common.ShowProgressBar
import com.dokari4.githubapicompose.ui.components.common.TextSearchBar
import com.dokari4.githubapicompose.ui.components.empty.EmptyContent
import com.dokari4.githubapicompose.ui.components.error.ErrorContent

@Composable
fun SearchScreen(
    onCardClick: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    val state = uiState

    // Add Event Listener when error occurred

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TextSearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onButtonClick = {
                viewModel.searchUser(it)
            }
        )

        when (state) {
            UIState.Initial -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Please Search User")
                }
            }

            UIState.Loading -> ShowProgressBar()

            is UIState.Success -> {
                Spacer(modifier = Modifier.height(16.dp))
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
                                .clickable(onClick = { onCardClick(data.login) }),
                            avatarUrl = data.avatarUrl,
                            username = data.login
                        )
                    }
                }
            }

            is UIState.Error -> ErrorContent(
                errorMessage = state.errorMessage,
                onClickRetry = { },
                isButtonEnabled = false,
                textButton = ""
            )

            UIState.Empty -> EmptyContent("No User Found")
        }
    }
}