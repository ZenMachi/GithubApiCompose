package com.dokari4.githubapicompose.ui.components.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.dokari4.githubapicompose.ui.detail.DetailScreenState
import com.dokari4.githubapicompose.ui.detail.DetailViewModel

@Composable
fun FollowTabRow(
    username: String,
    viewModel: DetailViewModel,
    state: DetailScreenState,
    onNavigate: (String) -> Unit,
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
            Box(modifier = Modifier.fillMaxSize()) {
                when (index) {
                    0 -> {
                        ListFollow(
                            onFetchData = { viewModel.getFollowersUser(username) },
                            state = state,
                            onNavigate = onNavigate
                        )
                    }

                    1 -> {
                        ListFollow(
                            onFetchData = { viewModel.getFollowingUser(username) },
                            state = state,
                            onNavigate = onNavigate
                        )
                    }
                }

            }
        }
    }
}

data class TabItem(val title: String)