package com.dokari4.githubapicompose.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.dokari4.githubapicompose.ui.components.ShowProgressBar

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    username: String,
    viewModel: DetailViewModel,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getDetailUser(username)
    }

    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            if (state.isLoading) ShowProgressBar()
            Row { Text("This is Detail Screen: ${state.data?.name}") }
        }
    }
}