package com.dokari4.githubapicompose.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.dokari4.githubapicompose.R
import com.dokari4.githubapicompose.ui.components.ShowProgressBar
import com.dokari4.githubapicompose.utils.formatNumber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    username: String,
    viewModel: DetailViewModel,
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getDetailUser(username)
    }
    if (state.isLoading) ShowProgressBar()

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
                    .padding(horizontal = 16.dp)
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
            }
        }
    }
}

@Composable
private fun Bio(bio: String) {
    Text("Bio", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(4.dp))
    Text(bio, style = MaterialTheme.typography.bodyMedium)
}

@Composable
private fun ProfileBar(
    photoUrl: String,
    name: String?,
    repoCount: Long,
    followerCount: Long,
    followingCount: Long
) {
    Row(
        modifier = Modifier
            .height(128.dp)
    ) {
        val placeholder = rememberVectorPainter(Icons.Default.Person)

        AsyncImage(
            modifier = Modifier
                .height(128.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Fit,
            placeholder = placeholder,
            model = photoUrl,
            contentDescription = "Photo of $name"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (name != null) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Stats(
                    title = "Repo",
                    count = formatNumber(repoCount)
                )
                Stats(
                    title = "Followers",
                    count = formatNumber(followerCount)
                )
                Stats(
                    title = "Following",
                    count = formatNumber(followingCount)
                )
            }
        }
    }
}


@Composable
private fun Stats(
    title: String,
    count: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextBox(text = count)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun TextBox(
    text: String,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .width(64.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 4.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
        )
    }
}