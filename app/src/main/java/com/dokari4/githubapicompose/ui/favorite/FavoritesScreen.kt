package com.dokari4.githubapicompose.ui.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dokari4.githubapicompose.ui.components.common.CardItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCardClick: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favorites") },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            // TODO: Implement Swipe to Delete per Item
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(state) { index, data ->
                    val lastPadding = if (index == state.lastIndex) 16.dp else 0.dp
                    CardItem(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = lastPadding)
                            .fillMaxWidth()
                            .clickable(onClick = { onCardClick(data.username) }),
                        avatarUrl = data.avatarUrl,
                        username = data.username
                    )
                }
            }
        }
    }
}