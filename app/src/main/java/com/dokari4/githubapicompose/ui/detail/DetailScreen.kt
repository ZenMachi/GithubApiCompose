package com.dokari4.githubapicompose.ui.detail

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.dokari4.githubapicompose.ui.components.CardItem
import com.dokari4.githubapicompose.ui.components.ShowProgressBar
import com.dokari4.githubapicompose.ui.components.StatsItem
import com.dokari4.githubapicompose.utils.formatNumber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    username: String,
    viewModel: DetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit
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
                FollowTabRow(username, viewModel, state)
            }
        }
    }
}

@Composable
private fun FollowTabRow(
    username: String,
    viewModel: DetailViewModel,
    state: DetailScreenState
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf(
        TabItem("Followers"),
        TabItem("Following")
    )

    val pagerState = rememberPagerState {
        tabs.size
    }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = selectedTabIndex
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    text = { Text(tab.title) },
                    selected = index == selectedTabIndex,
                    onClick = { selectedTabIndex = index }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { index ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                when (index) {
                    0 -> {
                        LaunchedEffect(Unit) {
                            viewModel.getFollowersUser(username)
                        }

                        if (state.isLoadingFollowers) ShowProgressBar()

                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            itemsIndexed(state.listFollowers) { index, data ->
                                if (index == 0) Spacer(modifier = Modifier.height(16.dp))
                                CardItem(
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                        .fillMaxWidth(),
                                    data = data
                                )
                            }
                        }
                    }

                    1 -> {
                        LaunchedEffect(Unit) {
                            viewModel.getFollowingUser(username)
                        }

                        if (state.isLoadingFollowing) ShowProgressBar()

                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            itemsIndexed(state.listFollowing) { index, data ->
                                if (index == 0) Spacer(modifier = Modifier.height(16.dp))
                                CardItem(
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                        .fillMaxWidth(),
                                    data = data
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}

data class TabItem(val title: String)


@Composable
private fun Bio(bio: String) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text("Bio", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(bio, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun ProfileBar(
    height: Dp = 128.dp,
    photoUrl: String,
    name: String?,
    repoCount: Long,
    followerCount: Long,
    followingCount: Long
) {
    Row(
        modifier = Modifier
            .height(height)
            .padding(horizontal = 16.dp)
    ) {
        val placeholder = rememberVectorPainter(Icons.Default.Person)

        AsyncImage(
            modifier = Modifier
                .height(height)
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
                StatsItem(
                    title = "Repo",
                    count = formatNumber(repoCount)
                )
                StatsItem(
                    title = "Followers",
                    count = formatNumber(followerCount)
                )
                StatsItem(
                    title = "Following",
                    count = formatNumber(followingCount)
                )
            }
        }
    }
}