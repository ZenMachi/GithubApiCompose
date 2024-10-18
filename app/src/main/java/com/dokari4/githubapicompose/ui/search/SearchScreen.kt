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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dokari4.githubapicompose.ui.components.CardItem
import com.dokari4.githubapicompose.ui.components.ShowProgressBar
import com.dokari4.githubapicompose.ui.components.TextSearchBar

/*
TODO 01: Create SearchScreen
TODO 02: Create SearchViewModel
TODO 03: Create UiState
TODO 04: Add Network Call in Api
*/

@Composable
fun SearchScreen(
    onCardClick: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column {
        TextSearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onButtonClick = {
               viewModel.searchUser(it)
            }
        )
        if (state.isLoading) ShowProgressBar()
        if (state.users.isEmpty() && state.firstTime) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Please Search User")
            }
        }
        if (state.users.isNotEmpty())  {
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(state.users) { index, data ->
                    val lastPadding = if (index == state.users.lastIndex) 16.dp else 0.dp
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
        if (state.users.isEmpty() && !state.firstTime) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No Result")
            }
        }
    }
}