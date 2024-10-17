package com.dokari4.githubapicompose.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dokari4.githubapicompose.ui.components.Bio
import com.dokari4.githubapicompose.ui.components.ShowProgressBar
import com.dokari4.githubapicompose.ui.components.detail.FollowTabRow
import com.dokari4.githubapicompose.ui.components.detail.ProfileBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    username: String,
    viewModel: DetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onNavigateToDetailScreen: (String) -> Unit,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getDetailUser(username)
    }
    if (state.isLoadingScreen) ShowProgressBar()

    if (state.data != null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(state.data?.login!!, fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(
                            onClick = { onBackClick() }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                ProfileBar(
                    photoUrl = state.data?.avatarUrl ?: "",
                    name = state.data?.name,
                    repoCount = state.data?.publicRepo?.toLong() ?: 0,
                    followerCount = state.data?.followers?.toLong() ?: 0,
                    followingCount = state.data?.following?.toLong() ?: 0
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (state.data?.bio != null) {
                    Bio(bio = state.data?.bio!!)
                    Spacer(modifier = Modifier.height(16.dp))
                }
                FollowTabRow(username, viewModel, state, onNavigateToDetailScreen)
            }
        }
    }
}
