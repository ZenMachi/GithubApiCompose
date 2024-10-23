package com.dokari4.githubapicompose.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dokari4.githubapicompose.ui.UIState
import com.dokari4.githubapicompose.ui.components.Bio
import com.dokari4.githubapicompose.ui.components.ShowProgressBar
import com.dokari4.githubapicompose.ui.components.detail.FollowTabRow
import com.dokari4.githubapicompose.ui.components.detail.ProfileBar
import com.dokari4.githubapicompose.ui.components.lottie.ErrorLottie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    username: String,
    viewModel: DetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onNavigateToDetailScreen: (String) -> Unit,
) {
    val detailUserUiState by viewModel.detailUserState.collectAsState()
    val followersUiState by viewModel.followersUserState.collectAsState()
    val followingUiState by viewModel.followingUserState.collectAsState()

    val detailUserState = detailUserUiState
    val followersState = followersUiState
    val followingState = followingUiState

    LaunchedEffect(Unit) {
        viewModel.getDetailUser(username)
        viewModel.getFollowingUser(username)
        viewModel.getFollowersUser(username)
    }
    //TODO: Always Use Scaffold and handle error with lottie animation
    when (detailUserState) {
        UIState.Initial, UIState.Loading -> ShowProgressBar()
        is UIState.Success -> {
            val data = detailUserState.data

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(data.login, fontWeight = FontWeight.Bold) },
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
                val listFollowers = if (followersState is UIState.Success) {
                    followersState.data
                } else emptyList()
                val listFollowing = if (followingState is UIState.Success) {
                    followingState.data
                } else emptyList()
                val isLoadingFollowers = followersState is UIState.Loading
                val isLoadingFollowing = followingState is UIState.Loading

                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    ProfileBar(
                        photoUrl = data.avatarUrl,
                        name = data.name,
                        repoCount = data.publicRepo.toLong(),
                        followerCount = data.followers.toLong(),
                        followingCount = data.following.toLong()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    if (data.bio != null) {
                        Bio(bio = data.bio)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    FollowTabRow(
                        isLoadingFollowing = isLoadingFollowing,
                        isLoadingFollowers = isLoadingFollowers,
                        listFollowers = listFollowers,
                        listFollowing = listFollowing,
                        onNavigate = onNavigateToDetailScreen
                    )
                }
            }
        }
        is UIState.Error -> {
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
                    ErrorLottie()
                }
                Text(text = detailUserState.errorMessage, textAlign = TextAlign.Center)
            }
        }
        UIState.Empty -> {

        }
    }
}
