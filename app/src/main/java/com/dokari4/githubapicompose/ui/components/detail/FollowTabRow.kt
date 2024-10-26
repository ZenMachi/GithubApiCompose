package com.dokari4.githubapicompose.ui.components.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import com.dokari4.githubapicompose.data.remote.dto.UserDto

@Composable
fun FollowTabRow(
    isLoadingFollowing: Boolean,
    isLoadingFollowers: Boolean,
    listFollowers: List<UserDto>,
    listFollowing: List<UserDto>,
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
                    text = { Text(tab.title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold) },
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
                            listUser = listFollowers,
                            isLoading = isLoadingFollowers,
                            onNavigate = onNavigate
                        )
                    }

                    1 -> {
                        ListFollow(
                            listUser = listFollowing,
                            isLoading = isLoadingFollowing,
                            onNavigate = onNavigate
                        )
                    }
                }

            }
        }
    }
}

data class TabItem(val title: String)